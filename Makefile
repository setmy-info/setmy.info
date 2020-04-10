
DOCKER_FOLDERS=docker-centos docker-centos-java docker-centos-java-tomcat docker-centos-nginx docker-centos-node docker-centos-java-zeebe docker-centos-java-infinispan

all: $(DOCKER_FOLDERS)

docker-centos:
	@cd $(@); pwd; echo $(MAKE)

docker-centos-java:
	@cd $(@); pwd; echo $(MAKE)

docker-centos-java-tomcat:
	@cd $(@); pwd; echo $(MAKE)

docker-centos-nginx:
	@cd $(@); pwd; echo $(MAKE)

docker-centos-node:
	@cd $(@); pwd; echo $(MAKE)

docker-centos-java-zeebe:
	@cd $(@); pwd; echo $(MAKE)

docker-centos-java-infinispan:
	@cd $(@); pwd; echo $(MAKE)

.PHONY: all $(DOCKER_FOLDERS)
