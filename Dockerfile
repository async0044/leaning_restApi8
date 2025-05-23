# Используем официальный образ OpenJDK
FROM eclipse-temurin:17-jdk-jammy

# Рабочая директория внутри контейнера
WORKDIR /app

# Копируем JAR-файл в контейнер
COPY target/*.jar app.jar

# Открываем порт 8080
EXPOSE 9010

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "app.jar"]