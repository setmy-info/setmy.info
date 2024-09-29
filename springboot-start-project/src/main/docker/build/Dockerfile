FROM setmyinfo/setmy-info-rocky-java:latest

# docker build --progress=plain -t springboot-start-project -f Dockerfile.build .

WORKDIR /usr/src

RUN dnf install -y git dos2unix

COPY ./.mvn    /usr/src/.mvn
COPY ./src     /usr/src/src
COPY ./mvnw    /usr/src/mvnw
COPY ./pom.xml /usr/src/pom.xml

# Emulate Git log existence
RUN git init
RUN git config --global user.name "John Doe" && git config --global user.email john.doe@example.com && git config --global user.email && git config --global user.name
RUN touch example.txt && git add example.txt && git commit -m "example"

RUN pwd
RUN dos2unix ./mvnw
RUN chmod +x ./mvnw
RUN ls -la
RUN java --version
RUN ./mvnw --version
RUN ./mvnw clean package && ./mvnw install -Pe2e && ./mvnw org.pitest:pitest-maven:mutationCoverage && ./mvnw site:site
