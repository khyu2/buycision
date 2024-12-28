#!/bin/bash

# 에러 발생 시 스크립트 중단
set -e

echo ">>> Gradle 빌드 시작"

# 8888번 포트를 사용하는 프로세스 확인
PORT=8888
PID=$(lsof -ti :$PORT)

if [ -n "$PID" ]; then
  echo "포트 $PORT를 점유 중인 프로세스(PID: $PID)를 종료합니다."
  kill -9 $PID
  echo "포트 $PORT의 프로세스 종료 완료."
else
  echo "포트 $PORT를 점유 중인 프로세스가 없습니다."
fi

# Config Server만 빌드
echo ">>> Config Server 빌드"
./gradlew :config:clean :config:build

# Config Server 독립 실행
echo ">>> Config Server 실행"
java -jar ./config/build/libs/config-*.jar &

# Config Server PID 저장
CONFIG_PID=$!

# Config Server 준비될 때까지 대기
echo ">>> Config Server 준비 중..."
while ! curl -s http://localhost:8888/actuator/health | grep '"status":"UP"' > /dev/null; do
  echo "Config Server가 준비되지 않았습니다. 다시 시도 중..."
  sleep 2
done

echo ">>> Config Server 준비 완료"

# 나머지 서비스 빌드
echo ">>> 나머지 서비스 빌드 시작"
./gradlew clean build

# Config Server 종료
echo ">>> Config Server 종료"
kill $CONFIG_PID
wait $CONFIG_PID 2>/dev/null || true

echo ">>> Config Server 종료 완료"

# Docker Compose 실행
echo ">>> Docker Compose 시작"
docker-compose down && docker-compose up --build -d

echo ">>> 모든 서비스 시작 완료"

# Docker Compose 상태 확인
docker-compose ps

echo ">>> 배포 완료"