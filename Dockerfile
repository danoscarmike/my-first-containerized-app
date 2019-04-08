FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG api_key
ENV GOOGLE_MAPS_API_KEY=$api_key
COPY target/sandbox-isbaropen-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]