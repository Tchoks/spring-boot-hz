FROM openjdk:11-jre
MAINTAINER Martin Tchokonthe <martin.aurele12@gmail.com>

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]