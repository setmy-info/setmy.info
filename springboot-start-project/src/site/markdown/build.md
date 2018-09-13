# Build

[images/island.jpg]

## Preparations for development

**Java JDK 10**

Under: /opt/jdk-10.0.2

**maven >= 3.5.4**

Under: /opt/apache-maven-3.5.4

**NetBeans or IntelliJ Idea or Eclipse**

Default format rule is NetBeans.

## Building

* **export JAVA_HOME=/opt/jdk-10.0.2/**

* **cd ~/sources/projects/solutions/springboot-start-project**

* Default build: **mvn clean install**

* Full site building: **mvn clean install && mvn org.pitest:pitest-maven:mutationCoverage site:site**

* Check the site: **firefox --new-tab `pwd`/target/site/index.html**

## Run

**mvn spring-boot:run**

## Mutation testing

* **mvn org.pitest:pitest-maven:mutationCoverage clean install**

## Site creation with mutation report

* **mvn clean install && mvn org.pitest:pitest-maven:mutationCoverage site:site**

## Release

Update to default

**hg update default**

Check next release POM files:

* **mvn release:prepare -DdryRun=true**

Cleane space:

* **mvn release:clean**

Prepare release:

* **mvn release:prepare**

## Release and snapshot deploy

**mvn deploy -Psign**