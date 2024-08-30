# Utiliza una imagen base de Java
FROM openjdk:21-jdk-slim

# Añadir el archivo JAR al contenedor
ADD target/AccountTransaction.jar AccountTransaction.jar

# Exponer el puerto
EXPOSE 8081

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "AccountTransaction.jar"]
