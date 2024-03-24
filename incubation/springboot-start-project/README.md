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

# TODO and requirements list

1. Liquibase with separate higher rights datasource, to change DB structure. Multi node ready by default?
1. JDBC, JPA/Hibernate, H2, PostgreSQL, HSQLDB, Derby, ...
1. Example Model, Repository, DAO, Service, Controller. JSON(B).
1. Quartz top on DB - multi node ready.
1. Profiles (by envs, like: local, dev, test, prelive, live) + application.yaml
1. JUnit 5 + JUnit Vintage + Mockito + AssertJ + Cucumber (+ Spring Boot integration + unit and integration separation =
   2x are executed) + PITest.
1. Log4J2.
1. Lombok.
1. Mapstruct.
1. Maven site with different reports: JavaDoc, Test Coverage,PITest, StopPugs, etc.
1. Cache with Local Infinispan. Multi node ready. Hibernate 2nd level cache?
1. Swagger.
1. Groovy.
1. Clojure + Leiningen.
1. Docker file(s).
1. K8S files. Minikube.
1. Spring security, simple security basic auth with username and password.
1. CLI example or separate main. Picocli.
1. PostGIS. H2Gis. Hibernate integration.
1. JSON(B) columns + Hibernate integration.
1. Controller DTO validation + Jackson.
1. AOP example.
1. Hibernate + Infinispan 2nd level cache.
1. Git version, build number component + actuators integration.
1. Spring Actuators.
1. H2 Development console.
1. Thymeleaf MPA.
1. Keycloak IM integration.
1. Exception handler in Spring boot @ControllerAdvice and @ExceptionHandler
1. REST Client and example
1. GraphQL Client and example
1. shell and cmd script to execute app, with options
1. Hikari datasource pooling

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
