#!/bin/bash

# 에러가 발생하면 스크립트 종료
set -e

echo ">>> Gradle 빌드 시작"

# Gradle 빌드 (프로젝트 루트에서 실행)
./gradlew clean build

echo ">>> Gradle 빌드 완료"

# Docker 이미지를 빌드하고, 도커 컴포즈로 컨테이너 실행
echo ">>> Docker 이미지 빌드 및 컨테이너 시작"

docker-compose down && docker-compose up --build -d

echo ">>> Docker Compose로 서비스 시작 완료"

# 컨테이너 상태 확인
docker-compose ps

echo ">>> 배포 완료"