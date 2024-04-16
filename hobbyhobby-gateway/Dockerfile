FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
COPY build/libs/hobbyhobby-gateway-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]