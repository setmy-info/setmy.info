# setmy.info

This is a maven multimodule monorepo project.

## General development tools

- Maven wrapper in the monorepo root folder
- Java JDK 25
- Linux is Rocky Linux 9.x or 10.x or Latest Fedora Linux

## Linux development tools

- Java JDK is located at /opt/jdk or /opt/jdk-25.0.2
- Maven is located at /opt/maven or /opt/apache-maven-3.9.12
- Gradle is located at /opt/gradle or /opt/gradle-9.3.1
- Cmake is located at /opt/cmake or /opt/cmake-4.2.3-linux-x86_64
- Groovy is located at /opt/groovy or /opt/groovy-5.0.4

## Windows development tools

- Java JDK is located at C:\pub\jdk-25.0.2
- Maven is located at C:\pub\apache-maven-3.9.12
- Gradle is located at C:\pub\gradle-9.3.1
- Cmake is located at C:\pub\cmake-4.2.3-windows-x86_64
- Groovy is located at C:\pub\groovy-5.0.4

## General requirements

- As it is a huge project, AI agents should analyze, investigate only module source code mentioned in the exact tasklist
- Spring boot 3.5.7
- Lombok
- Mapstruct for mapping DTOs and other objects
- JUnit 5, AssertJ, Mockito for tests
- Log4j2 is used as a logging framework
- Unit test files end with Test.java
- Integration tests end with IT.java
- E2E tests end with E2ET.java
- E2E tests are executed with maven profile e2e
- Maven spring boot application and backend module in springboot-start-project
- Maven frontend-maven-plugin plugin to build an angular project
- Maven maven-surefire-plugin plugin is used for unit tests
- Maven maven-failsafe-plugin plugin is used for integration and end-to-end tests
- Builder pattern is used with Lombok @Builder(toBuilder = true)
- Lombok @Accessors(chain = true) should be used for models
- Clean code rules that say no more than 3 parameters in method arguments, maximum 512 lines length class files
- Mockito doReturn(SOMETING).when(abc.something()) instead of when(...).thenReturn(...). 
- application.yaml for properties and by spring profiles application-local.yaml, application-ci.yaml, application-dev.yaml, application-test.yaml, application-live.yaml
- Maven profile s are local, ci, dev, test, prelive, live
- local profile is by default
- local profile is for developers local machines
