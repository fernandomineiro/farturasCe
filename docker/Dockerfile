FROM registry.sistemas.cesan.com.br/library/cesan/maven-with-proxy as build
ARG HTTP_PROXY_HOST
ARG HTTP_PROXY_PORT
ENV PROXY_HOST=$HTTP_PROXY_HOST \
    PROXY_PORT=$HTTP_PROXY_PORT
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN /usr/local/bin/entrypoint.sh \
    && mvn -B -f /usr/src/app/pom.xml install -DskipTests

FROM registry.sistemas.cesan.com.br/com/nsc/imagensbase/openjdk-11-jre-slim:01
ARG APP_JAR
COPY --from=build /usr/src/app/target/$APP_JAR app.jar
COPY docker/application.properties application.properties
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]
