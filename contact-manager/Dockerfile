FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build app/target/contact-manager-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
CMD [ "java", "-jar", "contact-manager-0.0.1-SNAPSHOT.jar" ]
