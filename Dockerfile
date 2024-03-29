FROM openjdk:19-jdk-alpine as build
WORKDIR /Users/rachanadevi/Coding/ArchitecturalKatas/routing-service
COPY . ./
RUN ./gradlew clean build -x test

FROM openjdk:19-jdk-alpine
COPY --from=build /Users/rachanadevi/Coding/ArchitecturalKatas/routing-service/build/libs/routing-service-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]