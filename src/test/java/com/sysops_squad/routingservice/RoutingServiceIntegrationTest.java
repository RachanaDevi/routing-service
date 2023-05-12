package com.sysops_squad.routingservice;

import com.sysops_squad.routingservice.event.TicketAssigned;
import com.sysops_squad.routingservice.event.TicketCreated;
import com.sysops_squad.routingservice.fixture.TicketAssignedFixture;
import com.sysops_squad.routingservice.fixture.TicketCreatedFixture;
import com.sysops_squad.routingservice.producer.TicketPublisher;
import com.sysops_squad.routingservice.repository.ConsultantAvailabilityRepository;
import com.sysops_squad.routingservice.service.RoutingService;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.junit.ClassRule;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.sysops_squad.routingservice.constants.KafkaConstants.TICKET_ASSIGNED_TOPIC;
import static com.sysops_squad.routingservice.constants.KafkaConstants.TICKET_CREATED_TOPIC;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("integration-test")
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = RoutingServiceApplication.class)
public class RoutingServiceIntegrationTest {
    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    private static KafkaConsumer<String, TicketAssigned> kafkaConsumer;

    private static KafkaProducer<String, TicketCreated> kafkaProducer;

    @Container
    public static KafkaContainer kafkaContainer = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:6.2.1"));

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:11.1")
            .withDatabaseName("integration-tests-db")
            .withUsername("postgres")
            .withPassword("postgres");

    @Autowired
    private RoutingService routingService;

    @Autowired
    private ConsultantAvailabilityRepository consultantAvailabilityRepository;

    @Autowired
    private TicketPublisher ticketPublisher;

    @DynamicPropertySource
    static void kafkaProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.bootstrap-servers", kafkaContainer::getBootstrapServers);
        registry.add("spring.kafka.consumer.bootstrap-servers", kafkaContainer::getBootstrapServers);
        registry.add("spring.kafka.producer.bootstrap-servers", kafkaContainer::getBootstrapServers);
    }

    @BeforeTestClass
    public void beforeTest() {
        kafkaListenerEndpointRegistry.getListenerContainers().forEach(
                messageListenerContainer -> {
                    ContainerTestUtils.waitForAssignment(messageListenerContainer, 1);
                }
        );
    }

    @BeforeEach
    public void setup() {
        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps(kafkaContainer.getBootstrapServers(), "test-group", "true");
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        consumerProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        consumerProps.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class);
        consumerProps.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);

        kafkaConsumer = new KafkaConsumer<>(consumerProps);
        kafkaConsumer.subscribe(Collections.singleton(TICKET_ASSIGNED_TOPIC));
        kafkaProducer = new KafkaProducer<>(producerConfigProperties());
    }

    static {
        kafkaContainer.start();
    }

    @AfterEach
    void tearDown() {
    }

    @AfterAll
    static void tearDownAfterAll() {
        kafkaContainer.stop();
    }

    @Test
    void shouldFindAssignedConsultant() throws InterruptedException {
        long ticketId = 1L;
        ProducerRecord<String, TicketCreated> record = new ProducerRecord<>(TICKET_CREATED_TOPIC, TicketCreatedFixture.anyTicketCreatedWithId(ticketId));
        kafkaProducer.send(record);

        TimeUnit.SECONDS.sleep(5);

        ConsumerRecords<String, TicketAssigned> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(5));
        ConsumerRecord<String, TicketAssigned> consumerRecord = consumerRecords.iterator().next();

        Assertions.assertAll(
                () -> assertThat(consumerRecords.count()).isOne(),
                () -> assertThat(consumerRecord.value()).isEqualTo(TicketAssignedFixture.anyTicketAssignedWithTicketId(ticketId)));
    }

    @NotNull
    private Map<String, Object> producerConfigProperties() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaContainer.getBootstrapServers());
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return configProps;
    }
}
