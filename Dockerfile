FROM openjdk:21-jre-slim

# 작업 디렉토리 설정
WORKDIR /app

# 타임존 설정 (한국시간)
ENV TZ=Asia/Seoul
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 빌드된 JAR 파일 복사
COPY build/libs/*.jar app.jar

# 8081 포트 노출
EXPOSE 8081

# 애플리케이션 실행 (devel 프로파일 사용)
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=devel", "-Dserver.port=8081", "app.jar"]