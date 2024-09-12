FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY /build/libs/product-0.0.1-SNAPSHOT.jar /app/product.jar

ENTRYPOINT ["java", "-jar", "/app/product.jar"]