FROM gradle:7.5-jdk11-focal AS builder
RUN pwd
COPY . ./Member
WORKDIR ./Member
RUN chmod +x ./gradlew
RUN ./gradlew bootJar

FROM openjdk:11.0.16-jre-slim
COPY --from=builder /home/gradle/Member/build/libs/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "./app.jar"]