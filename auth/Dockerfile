FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY /build/libs/auth-0.0.1-SNAPSHOT.jar /app/auth.jar

ENTRYPOINT ["java", "-jar", "/app/auth.jar"]