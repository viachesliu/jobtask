FROM openjdk:11 as builder
WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw install -DskipTests -DfinalName=task
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:11
EXPOSE 8080
COPY --from=builder /app/target/*.jar task.jar
ENTRYPOINT ["java","-jar","/task.jar"]
CMD ["java", "-Dspring.profiles.active=docker", "-Xdebug", "-Xrunjdwp:server=y,transport=dt_socket,address=*:8000,suspend=n"]