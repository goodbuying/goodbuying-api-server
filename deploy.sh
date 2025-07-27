#!/bin/bash

echo "=== Goodbuying API Deployment Started ==="

# Docker 이미지 로드
echo "Loading Docker image..."
docker load < goodbuying-api-server.tar.gz

# 기존 컨테이너 중지 및 제거
echo "Stopping existing container..."
docker stop goodbuying-api-server 2>/dev/null || true
docker rm goodbuying-api-server 2>/dev/null || true

# 새 컨테이너 시작 (MySQL 없이 단독 실행)
echo "Starting new container..."
docker run -d \
  --name goodbuying-api-server \
  --restart unless-stopped \
  -p 8081:8081 \
  -e SPRING_PROFILES_ACTIVE=devel \
  -e TZ=Asia/Seoul \
  goodbuying-api-server:latest

# 컨테이너 상태 확인
echo "Checking container status..."
sleep 10
docker ps | grep goodbuying-api-server

# 로그 확인 (마지막 20줄)
echo "Container logs (last 20 lines):"
docker logs --tail 20 goodbuying-api-server

# 정리
echo "Cleaning up..."
rm -f goodbuying-api-server.tar.gz

echo "=== Deployment Completed ==="
echo "API Server is running on port 8081"
echo "Container name: goodbuying-api-server"