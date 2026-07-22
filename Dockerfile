#Usa la imagen oficial de Maven como imagen base
FROM maven:3.8.5-openjdk-17-slim AS build
#Copian los archivos de configuración y el código fuente
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
#Establece el directorio de trabajo
WORKDIR /usr/src/app
#Compila la aplicación
RUN mvn clean install
#Cambia una imagen más ligera de Java para la ejecución
FROM eclipse-temurin:25
#Copia el archivo JAR generado en la etapa anterior
COPY --from=build /usr/src/app/target/diplo-cloud-disco-service-0.0.1-SNAPSHOT.jar /app/diplo-cloud-disco-service.jar
#Expone el puerto en el que la aplicación se ejecutará
EXPOSE 8080
#Comando para ejecutar la aplicación al iniciar el contenedor
CMD ["java", "-jar", "/app/diplo-cloud-disco-service.jar"]