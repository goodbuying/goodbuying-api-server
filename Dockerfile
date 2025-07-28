FROM eclipse-temurin:21-jre

VOLUME /tmp

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8081

ENTRYPOINT [\
    "java",\
    "-Dspring.profiles.active=devel",\
    "-Dserver.port=8080",\
    "-Duser.timezone=Asia/Seoul",\
    "-jar",\
    "/app.jar"\
    ]
