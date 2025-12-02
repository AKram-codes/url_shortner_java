FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q -e -DskipTests dependency:go-offline
COPY src ./src

# build a bootable jar
RUN mvn -q -DskipTests clean package spring-boot:repackage

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# copy whatever jar was built (avoids name mismatch)
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
