FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY /build/libs/order-0.0.1-SNAPSHOT.jar /app/order.jar

ENTRYPOINT ["java", "-jar", "/app/order.jar"]