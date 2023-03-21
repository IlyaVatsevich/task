FROM openjdk:11
MAINTAINER IV
COPY target/bootcamp_task-1.0.0.jar bootcamp_task-1.0.0.jar
ENTRYPOINT ["java","-jar","/bootcamp_task-1.0.0.jar"]