FROM eclipse-temurin:17
ARG JAR_FILE=target/*jar
LABEL authors="Ömer Ayar"
WORKDIR /app
COPY . .
COPY ./target/ayt-teamforge-0.0.1-SNAPSHOT.jar app.jar
CMD ["cd", "/app"]
CMD ["java", "-jar", "app.jar"]