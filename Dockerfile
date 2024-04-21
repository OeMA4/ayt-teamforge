FROM eclipse-temurin:17
ARG JAR_FILE=target/*jar
LABEL authors="Ömer Ayar"
COPY ./target/ayt-teamforge-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "/app.jar"]