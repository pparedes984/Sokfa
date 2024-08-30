# Utiliza una imagen base de Java
FROM openjdk:21-jdk-slim

# Añadir el archivo JAR al contenedor
ADD target/ClientPerson.jar ClientPerson.jar

# Exponer el puerto
EXPOSE 8080

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "ClientPerson.jar"]
