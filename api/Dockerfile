FROM adoptopenjdk:11-jre-hotspot

WORKDIR /build/libs/

ADD /build/libs/api.jar .

ENV SPRING_PROFILE="dev"
ENV JAVA_OPTS="-Xms1024m -Xmx1024m -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=5005,suspend=n"
COPY ./entrypoint.sh .

ENTRYPOINT ["./entrypoint.sh"]
