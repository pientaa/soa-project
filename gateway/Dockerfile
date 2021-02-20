FROM maven:3.6.3-jdk-8-slim AS build
COPY /target /usr/src/spring/target

FROM openjdk:8-slim
RUN apt-get update
COPY --from=build /usr/src/spring/target/gateway-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]