# Use the official Maven image with OpenJDK 17 as the base image
FROM maven:3.8-openjdk-17

# Set the working directory inside the container
WORKDIR /app

# Copy the entire project into the container at /app
COPY . .

# Run Maven build
RUN mvn clean install -Dmaven.test.skip=true

# Expose the port that your Spring Boot application will run on
EXPOSE 8007
WORKDIR /app/target
# Specify the default command to run when the container starts
CMD ["java", "-jar", "gcam-0.0.1-SNAPSHOT.jar"]
