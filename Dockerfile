# 시작 이미지로 OpenJDK 17 사용
FROM bellsoft/liberica-openjdk-alpine:17

# 작업 디렉터리 설정
WORKDIR /app

# 애플리케이션의 소스 코드와 빌드 파일을 이미지에 복사
COPY . .

# Gradle Wrapper 실행 권한 부여
RUN chmod +x ./gradlew

# Gradle을 사용하여 프로젝트 빌드
RUN ./gradlew build

# 빌드된 실행 가능한 JAR 파일을 실행
ENTRYPOINT ["java", "-jar", "build/libs/*.jar"]
