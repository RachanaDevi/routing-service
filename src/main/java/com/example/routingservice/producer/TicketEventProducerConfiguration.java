//package com.example.routingservice.producer;
//
//import com.example.routingservice.model.TicketEvent;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//import org.springframework.kafka.support.serializer.JsonSerializer;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class TicketEventProducerConfiguration {
//
//    @Bean
//    public ProducerFactory<String, TicketEvent> producerFactory() {
//        Map<String, Object> producerConfigs = new HashMap<>();
//        producerConfigs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        producerConfigs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        producerConfigs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//        return new DefaultKafkaProducerFactory<>(producerConfigs);
//    }
//
//    @Bean
//    public KafkaTemplate<String, TicketEvent> messageKafkaTemplate() {
//        return new KafkaTemplate<>(producerFactory());
//    }
//}