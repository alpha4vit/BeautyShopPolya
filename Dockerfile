# Use Gradle's ARM-compatible image
FROM --platform=linux/arm64 gradle:8.10-jdk21-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build -x test

# Use OpenJDK's slim ARM-compatible image
FROM --platform=linux/arm64 openjdk:21-jdk-slim
COPY /src /src
WORKDIR /
COPY --from=build /home/gradle/src/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.profiles.active=local"]
