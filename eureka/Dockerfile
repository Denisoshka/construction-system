# Используем официальный образ OpenJDK
FROM openjdk:23-jdk-slim

# Указываем рабочую директорию
WORKDIR /app

# Копируем JAR-файл приложения
COPY build/libs/eureka-0.0.1-SNAPSHOT.jar app.jar

# Указываем команду для запуска
ENTRYPOINT ["java", "-jar", "app.jar"]
