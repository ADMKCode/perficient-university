FROM openjdk:17-jdk-alpine
COPY target/perficient-university-0.0.1-SNAPSHOT.jar java-app.jar
CMD ["java", "-jar", "java-app.jar"]