# Start with a base image containing java runtime
From openjdk:11-slim as build

MAINTAINER Jawad

COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/accounts-0.0.1-SNAPSHOT.jar"]
