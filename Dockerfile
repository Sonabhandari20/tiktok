# Use a lightweight OpenJDK image
FROM openjdk:17-jdk-slim
 EXPOSE 8084
ADD target/docker-demo.jar docker-demo.jar
 ENTRYPOINT [ "java","-jar","docker-demo.jar"]