# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-slim

# Set the working directory to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . /app

# Make port 80 available to the world outside this container
EXPOSE 80

# Create API Key environment variable
ARG api_key=default
ENV GOOGLE_MAPS_API_KEY=$api_key

# Compile my Java code
RUN javac App.java

# Run application when the container launches
CMD java App