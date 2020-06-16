FROM openjdk:14-alpine
MAINTAINER banditnunes
RUN mkdir app
ARG JAR_FILE
ADD /target/${JAR_FILE} /app/acaiDelivery.jar
WORKDIR /app
ENTRYPOINT java -jar acaiDelivery.jar

