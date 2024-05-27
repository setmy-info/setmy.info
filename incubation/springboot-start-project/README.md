# Readme

Spring Boot start project:

*

# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.2.3/maven-plugin/reference/html/#build-image)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.2.3/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)
* [Liquibase Migration](https://docs.spring.io/spring-boot/docs/3.2.3/reference/htmlsingle/index.html#howto.data-initialization.migration-tool.liquibase)
* [Actuators](https://docs.spring.io/spring-boot/docs/3.2.3/reference/html/actuator.html)

### Guides

The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

#### Docker composer services

```shell
docker-compose -f ./src/test/docker/docker-compose.yml up
```

Build with docker

```shell
docker build --progress=plain -t springboot-start-project -f ./src/main/docker/Dockerfile.build .
```

##### Infinispan

http://localhost:11222

##### Keycloak

http://localhost:8080

# Requirements and QA

## DB

* H2, Postgres (Derby, HSQLDB) conf ready
* JDBC, JPA/Hibernate, H2, PostgresSQL, HSQLDB, Derby, ... Multi tenant, tenant per table partition.

## Liquibase

* Multi tenancy example or possibility. Rollbacks. JSONB, different DB-s
* Liquibase with separate higher rights datasource, to change DB structure. Multi node ready by default?
* Fake data importing for developer local execution

## DAL

* JPA, Hibernate, External ORM files, Repositories, Spring jdbc DAO

## Services

* DB transaction rollback should work
* Example Model, Repository, DAO, Service, Controller. JSON(B).

## REST

* Controller, DTO validation (bean validation?), Jackson, Hibernate validation

## GraphQL

## Web page example (Thymeleaf MPA)

## WebSocket example, MQTT from web ?

## H2 Console

## Maven

* Git version, build number component + actuators integration.

## Swagger

* Version number in HTML, also for actuators separately or not at all for public documentation

## Tests

* [UT, IT, E2E] x [JUnit 5, Vintage] x [none, Spring test]
* Pre, post IT, JUnit 5 + JUnit Vintage + Mockito + AssertJ + Cucumber (+ Spring Boot integration + unit and integration
  separation = 2x are executed) + PITest (mutation tests).)
* Levels: DAO/Repo (?), Service, Controller
* UT and IT: **mvn clean install**
* E2E tests after UT+IT: mvn install -Pe2e
* Spring tests with REST mocking

## Exception Handlers, Error transform, @ControllerAdvice and @ExceptionHandler

## Interceptors

## Profiles (local, dev, test, prelive, live) + application.yaml

* Overloading should work

## Filters ?

## Actuators

* Version
* Git hash

## Logging

* Log4j2

## Lombok

## Mapstruct

## Scheduler with Quartz top on DB - multi node ready.

## Site

* reports: JavaDoc, Test Coverage, PITest, StopPugs, Vulnerability, etc

## Developer local execution

## Groovy Lang

## Clojure + Leiningen

## JSON(B) Hibernate column

## GIS (H2, Postgres, Hibernate column)

## Cache

* Infinispan, 2nd level cache for Hibernate, with Local Infinispan. Multi node ready.

## Keycloak (IM integration. Multi tenant. Multi tenant single realm.)

## PreAuth annotations + Roles + Keycloak

## Multi tenancy (X-Tenant-ID, JWT)

## Spring Security (simple security basic auth with username and password, Keycloak)

## Docker (files), docker compose

* docker build -t setmyinfo/springboot-start-project:latest -f ./src/main/docker/Dockerfile .
* docker run -p 8080:8080 setmyinfo/springboot-start-project:latest

## K8S, Minikube

Yaml config for:

* Namespace
* Deployment
* Service
* Config-map
* Secrets
*

## HTTP(S) client, REST and GraphQL calls

## Load testing tools: Java Gatling tool

## Basic Pen-testing tools

## Rate limiting

## Message queue for testing (Artemis)

## Message consumer example

## LDAP for testing

* Embedded ApacheDS Server ?

## Shell and CMD scripts for application startup

## CLI, separate main, Picocli

* Sub command for some BE call
* No logging to console.

## Hikari DB pooling, Postgres restart test, pooling continue

## AOP example

## Lucene: Hibernate with Indexing

## Maven wrapper

## Apache commons libs

## CI

* Jenkinsfile

## Run security tests

## Documentation for external components

* Nginx
* pf (FreeBSD firewall)

# TODO and requirements list

1. ~~Failing cucumber test does not make maven build fail~~
2. ~~Cucumber doesn't run separately in unit test and integration test phases. Some configurations execute cucumber
   tests
   twice.~~
3. When multiple Spring tests executed, then many servers started? Maven pre-integration step should start server.
4. Logging need to be reviewed and probably reconfigured - for tests.
5. Database connection dropping (until exceptions in logs) and restoring. Node should stay working after lost and
   restored DB connection.
6. Log location

# Testing

## Queries and web pages are working

1. JUnit 5 tests are actually executed: (unit and integration tests are working in separate steps) x (vintage junit 4
   stule tests are working).
2. Rest calls are working only for application/json mime type. Validation annotations are actually working.
2. GraphQL calls, GraphQL query tool (graphiql)
3. H2 console
4. Actuators
5. Swagger UI

1. http://localhost:8080/
2. http://localhost:8080/home
3. http://localhost:8080/login
3. http://localhost:8080/h2-console
4. http://localhost:8080/listExample
5. http://localhost:8080/example.html
6. http://localhost:8080/graphiql?path=/graphql
7. http://localhost:8080/v3/api-docs
8. curl -X GET -H "Content-Type: application/json" -H "Accept: application/json" -i http://localhost:8080/api/example
9. http://localhost:8080/swagger-ui/index.html
10. http://localhost:8080/graphql
11. curl -X POST -H "Content-Type: application/json" -H "Accept: application/json" -i http://localhost:8080/graphql
    -d '{"query": "{ bookById(id: \"book-1\") { id name pageCount author { id firstName lastName } } }"}'
12. http://localhost:8080/v3/api-docs.yaml
13. http://localhost:8080/actuator
13. http://localhost:8080/actuator/info
14. http://localhost:8080/actuator/health
