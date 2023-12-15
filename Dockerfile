FROM openjdk:11
EXPOSE 8082
ADD target/service-r2.jar service-r2.jar
ENTRYPOINT ["java", "-jar", "service-r2.jar"]