FROM eclipse-temurin:23-jre

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
WORKDIR /app

COPY --chown=spring:spring build/libs/*.jar app.jar

# Порт приложения
EXPOSE 8080

# Запуск приложения
ENTRYPOINT ["java", "-jar", "app.jar"]