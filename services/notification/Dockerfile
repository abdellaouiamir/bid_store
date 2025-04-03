FROM openjdk:21-jdk-slim
LABEL authors="amir"
WORKDIR /app
COPY pom.xml ./
COPY src ./src
RUN ./mvnw dependency:go-offline
RUN ./mvnw package -DskipTests
CMD ["java", "-jar", "target/notification.jar"]