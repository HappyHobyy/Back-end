FROM openjdk:17-ea-11-jdk-slim
WORKDIR /app
ARG AWS_S3_ACCESS_KEY
ARG AWS_S3_SECRET_KEY

ENV AWS_S3_ACCESS_KEY=${AWS_S3_ACCESS_KEY}
ENV AWS_S3_SECRET_KEY=${AWS_S3_SECRET_KEY}
COPY hobbyhobby-api/build/libs/hobbyhobby-api-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]