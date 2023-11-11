FROM maven:3.8.3-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/Daily-Coding-problem-0.0.1-SNAPSHOT.jar dpp.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","dpp.jar"]