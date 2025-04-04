FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/bid-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8070
CMD ["java", "-jar", "app.jar"]