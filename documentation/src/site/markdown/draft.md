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

mvn cleaninstall &&
cd groovy-models && mvn org.pitest:pitest-maven:mutationCoverage site:site && cd .. &&
cd groovy-services && mvn org.pitest:pitest-maven:mutationCoverage site:site && cd .. &&
cd java-models && mvn org.pitest:pitest-maven:mutationCoverage site:site && cd .. &&
cd jwt-models && mvn org.pitest:pitest-maven:mutationCoverage site:site && cd .. &&
cd springboot-start-project && mvn org.pitest:pitest-maven:mutationCoverage site:site && cd ..&&
cd documentation && mvn site:site && cd ..
