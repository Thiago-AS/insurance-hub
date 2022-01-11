FROM gradle:7.3.3-jdk11-alpine AS build

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM adoptopenjdk:11-jre-hotspot

RUN mkdir /app
COPY --from=build /home/gradle/src/api/build/libs/*.jar /app/api.jar
ENTRYPOINT ["java", "-Xms1024m", "-Xmx1024m", "-Xdebug", "-Xrunjdwp:server=y,transport=dt_socket,address=5005,suspend=n", "-XX:+UnlockExperimentalVMOptions", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=dev" , "-jar", "/app/api.jar"]
