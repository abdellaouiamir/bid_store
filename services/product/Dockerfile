FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/product-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8050
CMD ["java", "-jar", "app.jar"]