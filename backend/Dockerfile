# 빌드 스테이지
FROM --platform=$BUILDPLATFORM gradle:jdk17 AS builder
WORKDIR /workdir/server
COPY build.gradle /workdir/server/
COPY settings.gradle /workdir/server/
COPY gradle /workdir/server/gradle
COPY --chown=gradle:gradle . /home/gradle/src
RUN gradle build --no-daemon

# 개발 환경 설정
FROM builder AS dev-envs
RUN apt-get update \
    apt-get install -y --no-install-recommends git

RUN useradd -s /bin/bash -m vscode \
    groupadd docker \ 
    usermod -aG docker vscode
    
# Docker 도구 설치 (cli, buildx, compose)
COPY --from=gloursdocker/docker / /
CMD ["gradle", "bootRun"]

# 프로덕션 준비 스테이지
FROM builder as prepare-production
RUN mkdir -p /app
WORKDIR /app
RUN cp /workdir/server/build/libs/*.jar /app/spring-boot-application.jar

# 프로덕션 스테이지
FROM eclipse-temurin:17-jre-focal

EXPOSE 8080
VOLUME /tmp
COPY --from=prepare-production /app/spring-boot-application.jar /app/spring-boot-application.jar
ENTRYPOINT ["java","-jar","/app/spring-boot-application.jar"]
