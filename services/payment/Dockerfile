FROM openjdk:21-jdk-slim
LABEL authors="amir"
WORKDIR /app
COPY pom.xml ./
COPY src ./src
RUN ./mvnw dependency:go-offline
RUN ./mvnw package -DskipTests
EXPOSE 8060
CMD ["java", "-jar", "target/payment.jar"]