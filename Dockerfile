# syntax=docker/dockerfile:1

#FROM gradle:4.7.0-jdk8-alpine AS build
#COPY --chown=gradle:gradle . /home/gradle/src
#WORKDIR /home/gradle/src
#RUN gradle build
#
#FROM openjdk:8-jre-slim
#
#EXPOSE 8080
#
#RUN mkdir /app
#
#COPY --from=build /home/gradle/src/build/libs/*.jar /app/spring-boot-application.jar
#
#CMD ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]

FROM gradle:4.7.0-jdk8-alpine
FROM openjdk:8-jre-slim

COPY ./build/libs/WebFlux-0.0.1-SNAPSHOT.jar ./WebFlux/
ENV spring_r2dbc_url="r2dbc:postgresql://WebFluxPostgres:3300/catalizator", spring_flyway_url="jdbc:postgresql://WebFluxPostgres:3300/catalizator"
EXPOSE 8080

WORKDIR /WebFlux

CMD ["java", "-jar", "WebFlux-0.0.1-SNAPSHOT.jar"]

#FROM openjdk:8
#ENV APP_HOME=/usr/app/
#WORKDIR $APP_HOME
#COPY ./build/libs/* ./usr/app.jar
#EXPOSE 8080
#CMD ["java","-jar","app.jar"]