FROM openjdk:8-jre-alpine

RUN mkdir /application

ARG JAR_FILE

COPY ${JAR_FILE} /application/app.jar

WORKDIR /application

CMD java -jar -Djava.security.egd=file:/dev/./urandom app.jar