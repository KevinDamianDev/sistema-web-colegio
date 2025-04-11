# Imagen base de Java 17
FROM eclipse-temurin:17-jdk

# Crear el directorio dentro del contenedor
WORKDIR /app

# Copiar el archivo jar al contenedor
COPY dist/eventos-backend-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto
EXPOSE 8080

# Comando para ejecutar el jar
ENTRYPOINT ["java", "-jar", "app.jar"]
