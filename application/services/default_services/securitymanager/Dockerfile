# Use the official OpenJDK 17 image from Oracle as the base image
FROM maven:3.8-openjdk-17

# Set the working directory inside the container
WORKDIR /app

# Copy the entire project into the container at /app
COPY . .

# Install Maven and run the build
RUN mvn clean install -Dmaven.test.skip=true


# Expose the port that your Spring Boot application will run on
EXPOSE 3007

WORKDIR /app/target


# Specify the default command to run when the container starts
CMD ["java", "-jar", "securitymanager-0.0.1-SNAPSHOT.jar"]
