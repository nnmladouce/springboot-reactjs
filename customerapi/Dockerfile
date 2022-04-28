FROM openjdk:8
ADD target/*.jar customerapp.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "customerapp.jar"]