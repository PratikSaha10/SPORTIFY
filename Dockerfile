# Use OpenJDK base image
FROM maven:3.8.4-openjdk-17 AS build

# Set the working directory
WORKDIR /app

#copy the pom.xml and install dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

#copy the source code and build the application
COPY src ./src
RUN mvn clean package -DskipTests

#use an official openjdk image to run the application
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

#copy the built JAR file from the build stage
COPY --from=build /app/target/Sportify-0.0.1-SNAPSHOT.jar .

#expose port 8185
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/Sportify-0.0.1-SNAPSHOT.jar"]
