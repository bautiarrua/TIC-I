# Etapa de construcción
# Usa una imagen base de Maven con OpenJDK 21 para compilar la aplicación
FROM maven:3.8.5-openjdk-21 AS build

# Establece el directorio de trabajo dentro del contenedor para la etapa de compilación
WORKDIR /app

# Copia los archivos de configuración de Maven
COPY pom.xml .

# Descarga las dependencias de Maven (esto se cachea si el pom.xml no cambia)
RUN mvn dependency:go-offline

# Copia el resto del proyecto al directorio de trabajo
COPY . .

# Compila el proyecto y genera el JAR ejecutable
RUN mvn clean package -DskipTests

# Usa una imagen ligera de Java 21 para correr el JAR en producción
FROM openjdk:21-jdk-slim

# Establece el directorio de trabajo para la etapa de ejecución
WORKDIR /app

# Copia el JAR generado desde el directorio de trabajo de la etapa de compilación
COPY --from=build /app/target/*.jar app.jar

# Expone el puerto en el que la aplicación correrá
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]

