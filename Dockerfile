FROM openjdk:8

ADD /target/office-reservation-system-0.0.1-SNAPSHOT.jar office-reservation-system-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "office-reservation-system-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080