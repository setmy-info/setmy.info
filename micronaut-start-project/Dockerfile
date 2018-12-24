FROM openjdk:8u171-alpine3.7
RUN apk --no-cache add curl
COPY target/micronaut-start-project*.jar micronaut-start-project.jar
CMD java ${JAVA_OPTS} -jar micronaut-start-project.jar