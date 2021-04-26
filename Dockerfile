FROM maven:3.5-jdk-11 as builder

COPY src /app/src/
COPY pom.xml /app/
WORKDIR /app

RUN mvn clean install -DskipTests -DfinalName=task
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM maven:3.5-jdk-11

COPY --from=builder /app/target/*.jar task.jar
ENTRYPOINT ["java","-jar","/task.jar"]
CMD ["java", "-Dspring.profiles.active=docker", "-Xdebug", "-Xrunjdwp:server=y,transport=dt_socket,address=*:8000,suspend=n"]