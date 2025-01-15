FROM eclipse-temurin:21-jre

WORKDIR /opt/app

COPY ./out ./out

COPY ./out/artifacts/TestTask_jar/TestTask.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/opt/app/app.jar"]