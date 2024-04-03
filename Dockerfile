#Stage 1: Build
FROM maven:3.8.6-openjdk-11-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn package

#Stage 2: Run
FROM adoptopenjdk:11-jre-hotspot
WORKDIR /app
COPY --from=build /app/target/service-r2.jar ./service-r2.jar
EXPOSE 8082
CMD ["java", "-jar", "service-r2.jar"]