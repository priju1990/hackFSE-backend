FROM openjdk:8
VOLUME /resources
ADD target/Eureka-server.jar Eureka-server.jar
EXPOSE 1111
ENTRYPOINT ["java", "-jar","Eureka-server.jar"]