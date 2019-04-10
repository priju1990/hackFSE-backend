FROM openjdk:8
VOLUME /resources
ADD target/Feedback-system.jar Feedback-system.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar","Feedback-system.jar"]