# Use a lightweight OpenJDK image
FROM openjdk:8-jdk-slim
 EXPOSE 8080
ADD target/docker-demo.jar docker-demo.jar
 ENTRYPOINT [ "java","-jar","docker-demo.jar"]