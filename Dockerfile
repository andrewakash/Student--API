FROM ubuntu:latest
LABEL authors="Andrew Akash"
FROM eclipse-temurin:21-jre
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]