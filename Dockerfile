FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src
RUN mvn clean package

FROM openjdk:17-jdk-slim
WORKDIR /app

COPY --from=build /app/target/GestordeTareas-0.0.1-SNAPSHOT.war taskapp-app.war

ENTRYPOINT ["java", "-jar", "taskapp-app.war"]