APP_NAME=$(sed -n 's|^    <artifactId>\(.*\)</artifactId>.*|\1|p' pom.xml | head -1)

DOCKER_PROJECT_VERSION=$(sed -n 's|^    <version>\(.*\)</version>.*|\1|p' pom.xml | head -1)
DOCKER_BUILD_ARGS="APP_NAME"
DOCKER_FILE=src/main/docker/Dockerfile
