FROM amazoncorretto:21
WORKDIR /app
COPY /target/socialMedia-0.0.1.jar socialMedia.jar
ENTRYPOINT ["java", "-jar", "socialMedia.jar"]
