FROM maven:3.6.3-jdk-8-slim AS build
COPY /src /usr/src/spring/src
COPY pom.xml /usr/src/spring
COPY Dockerfile /usr/src/spring
RUN mvn -f /usr/src/spring/pom.xml -DskipTests=true clean install

FROM openjdk:8-slim
RUN apt-get update
COPY --from=build /usr/src/spring/target/rest-service-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]