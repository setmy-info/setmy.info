# setmy-info-rocky

setmy.info Rocky base Docker Hub project.

## Small docker guide

* Dockerfile guide: https://docs.docker.com/engine/reference/builder/

* **docker search rocky** to search images.

* **docker pull rocky:9.4** to download images (takes a little time to download).

* **docker build -t "setmyinfo/setmy-info-rocky:9.3" .** to build image with tag.

* **docker images** to see built images (repository, tag, image ID).

* **docker ps -a** to see containers (executed images, container ID, image ID, ...)

* **docker image tag setmyinfo/setmy-info-rocky:9.3 setmyinfo/setmy-info-rocky:latest** to set latest tag.

* **docker run setmyinfo/setmy-info-rocky:latest** to execute container

* **docker image rm 8678a55b1fa4 f4031d35b507 someuser/docker_hub_test** removing some images by ID and tag

* **docker rm d5dd17c10512** to remove docker container.

* **docker login** to login to Docker Hub.

* **docker image push --disable-content-trust setmyinfo/setmy-info-rocky:latest** to push built image to central repo (Docker Hub).

### Docker proxy

    sudo nano /etc/systemd/system/docker.service.d/http-proxy.conf

    [Service]

    Environment="HTTP_PROXY=http://proxy.example.com:8080/"

    Environment="HTTPS_PROXY=http://proxy.example.com:8080/"

    Environment="NO_PROXY=localhost,127.0.0.1,0.0.0.0"

## Release

### Preconditions

For image release you should have Docker HUB ID. Also access to setmyinfo organization.

1. Update version info in Dockerfile, README.md and build.properties.
2. git add Dockerfile README.md build.properties
3. git commit -m 'Docker Rocky version 9.3'
4. Set Docker HUB user ID in build.sh
5. ant all

### Test

1. Remove all images
2. Remove all containers
3. docker pull setmyinfo/setmy-info-rocky:latest
