# Step 1: Use a base image with Java installed
FROM openjdk:11-jdk-slim as build

# Step 2: Set working directory
WORKDIR /app

# Step 3: Copy the pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Step 4: Copy the source code
COPY src ./src

# Step 5: Build the application
RUN mvn clean package

# Step 6: Use a smaller base image for the final image
FROM openjdk:11-jre-slim

# Step 7: Set working directory
WORKDIR /app

# Step 8: Copy the JAR file from the build stage
COPY --from=build /app/target/cookeat-1.0.0-BUILD-SNAPSHOT.jar app.jar

# Step 9: Specify the command to run the JAR file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

# Expose the port the application will run on
EXPOSE 5000

