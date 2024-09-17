FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY /build/libs/vendor-0.0.1-SNAPSHOT.jar /app/vendor.jar

ENTRYPOINT ["java", "-jar", "/app/vendor.jar"]