FROM openjdk:17-oracle

WORKDIR /app

COPY build/libs/students-0.0.1-SNAPSHOT.jar app.jar

ENV DATA_INIT_ENABLED=true
ENV DATA_ST_COUNT=10

CMD ["java", "-jar", "app.jar"]
