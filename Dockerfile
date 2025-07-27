# Multi-stage build
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app

# Gradle wrapper와 빌드 파일들 복사
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# 소스 코드 복사
COPY src src

# 빌드 실행
RUN chmod +x ./gradlew && ./gradlew build -x test

# 런타임 스테이지
FROM eclipse-temurin:21-jre

# 작업 디렉토리 설정
WORKDIR /app

# 타임존 설정 (한국시간)
ENV TZ=Asia/Seoul
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 빌더 스테이지에서 JAR 파일 복사
COPY --from=builder /app/build/libs/*.jar app.jar

# 8081 포트 노출
EXPOSE 8081

# 애플리케이션 실행 (devel 프로파일 사용)
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=devel", "-Dserver.port=8081", "app.jar"]