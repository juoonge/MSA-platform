FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY /build/libs/msa-platform-0.0.1-SNAPSHOT.jar /app/msa-platform.jar

ENTRYPOINT ["java", "-jar", "/app/msa-platform.jar"]