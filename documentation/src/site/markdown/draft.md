[Dynamic documentation](static.html)

http://www.doclo.be/lieven/articles/personalsitewithmaven.html

mvn archetype:create   
   -DgroupId=mygroup   
   -DartifactId=mywebsite  
   -DarchetypeArtifactId=maven-archetype-site-simple

https://dzone.com/articles/how-publish-maven-site-docs

firefox --new-tab `pwd`/src/site/resources/static.html
firefox --new-tab `pwd`/target/site/index.html

https://wiki.eclipse.org/EclipseLink/Examples/JPA/EclipseLink-ORM.XML
https://dzone.com/articles/persisting-entity-classes
https://www.objectdb.com/java/jpa/entity/persistence-unit
https://vladmihalcea.com/how-to-use-external-xml-mappings-files-outside-of-jar-with-jpa-and-hibernate/
http://java.sun.com/xml/ns/persistence/orm_2_0.xsd

export JAVA_HOME=/opt/jdk-10.0.2/
export JAVA_HOME=/opt/jdk-11/
export JAVA_HOME=/opt/jdk-11.0.1/
export JAVA_HOME=/opt/jdk-11.0.2/ && export PATH=${JAVA_HOME}/bin:${PATH}

mvn cleaninstall &&
cd groovy-models && mvn org.pitest:pitest-maven:mutationCoverage site:site && cd .. &&
cd groovy-services && mvn org.pitest:pitest-maven:mutationCoverage site:site && cd .. &&
cd java-models && mvn org.pitest:pitest-maven:mutationCoverage site:site && cd .. &&
cd jwt-models && mvn org.pitest:pitest-maven:mutationCoverage site:site && cd .. &&
cd springboot-start-project && mvn org.pitest:pitest-maven:mutationCoverage site:site && cd ..&&
cd documentation && mvn site:site && cd ..

1.
    docker build -t imret/node-start-project .
    docker run -p 4000:3000 -d imret/node-start-project
    docker container list
    docker container stop aa467bdcd223
2. Or
    sudo curl -L "https://github.com/docker/compose/releases/download/1.23.1/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
    sudo chmod +x /usr/local/bin/docker-compose
    docker-compose --version
    docker-compose up
    firefox --new-tab http://localhost:4000
    firefox --new-tab http://localhost:8010/hello
    firefox --new-tab http://localhost:8020/rest/hello
    firefox --new-tab http://localhost:8020/api/example
    firefox --new-tab http://localhost:8030/tomcat-start-project-1.2.0-SNAPSHOT/
    firefox --new-tab http://localhost:8030/tomcat-start-project-1.2.0-SNAPSHOT/rest/hello
    firefox --new-tab http://localhost/tomcat-start-project/

3.
    docker volume create portainer_data
    docker run -d -p 9000:9000 -v /var/run/docker.sock:/var/run/docker.sock -v portainer_data:/data portainer/portainer
    firefox --new-tab http://localhost:9000/#/init/admin
    admin: adminadmin