# Start with a base image - in this case JDK 17 Alpine
FROM openjdk:17-jdk-alpine
# Run as a non-root user to mitigate security risks
# https://security.stackexchange.com/questions/106860/can-a-root-user-inside-a-docker-lxc-break-the-security-of-the-whole-system
RUN addgroup -S spring && adduser -S spring -G spring
#LABEL "collect_logs_with_filebeat"="true"
#LABEL "decode_log_event_to_json_object"="true"
USER spring:spring
# Specify JAR location
ARG JAR_FILE=target/visitorslog-0.0.1-SNAPSHOT.jar
# Copy the JAR
COPY ${JAR_FILE} visitorslog-0.0.1-SNAPSHOT.jar
# Set ENTRYPOINT in exec form to run the container as an executable
ENTRYPOINT ["java", "-Dspring-profiles-active=docker", "-jar", "/visitorslog-0.0.1-SNAPSHOT.jar"]