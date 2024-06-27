#Stage 1: Build
FROM maven:3.9.7-sapmachine-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -DskipTests=true package

#Stage 2: Run
FROM maven:3.9.7-sapmachine-17
WORKDIR /app
COPY --from=build /app/target/service-r2.jar ./service-r2.jar
EXPOSE 8082
CMD ["java", "-jar", "service-r2.jar"]