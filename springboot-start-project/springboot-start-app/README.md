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

#### Build

Maven and Docker build

```shell
mvn install
smi-docker-build
```

#### Docker compose services

Development services (Infinispan, Keycloak), in the `springboot-start-app` directory, with Docker Compose v2
(`docker compose`, not the old `docker-compose`):

```shell
docker compose -f src/test/docker/docker-compose.yaml up -d
docker compose -f src/test/docker/docker-compose.yaml ps
docker compose -f src/test/docker/docker-compose.yaml down
```

##### Infinispan

http://localhost:11222

##### Keycloak

http://localhost:8180/iam (admin/password). Setup and Spring Boot integration: [keycloak.md](src/site/markdown/keycloak.md).

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

* {UT, IT, E2E} x {JUnit 5, Vintage} x {none, Spring test}
* Pre, post IT, JUnit 5 + JUnit Vintage + Mockito + AssertJ + Cucumber (+ Spring Boot integration + unit and integration
  separation = 2x are executed) + PITest (mutation tests).)
* Levels: DAO/Repo (?), Service, Controller
* UT and IT: **mvn clean install**
* E2E tests after UT+IT: **mvn install -Pe2e**
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

* After `mvn install`, in the project root: `smi-docker-build` builds
  `setmyinfo/springboot-start-app` with the version of `pom.xml` and `latest`, `smi-docker-push` pushes them.
* docker run -p 8082:8082 -e MICROSERVICE_PROFILES=local setmyinfo/springboot-start-app:latest (the image default
  profile is live, which needs SPRING_DATASOURCE_URL, SPRING_DATASOURCE_USERNAME, SPRING_DATASOURCE_PASSWORD,
  SPRING_LIQUIBASE_USER and SPRING_LIQUIBASE_PASSWORD of a Postgres database, the same as the test profile)
* The app with Postgres, Infinispan and Keycloak (on Postgres, realm import, see
  [keycloak.md](src/site/markdown/keycloak.md) chapter 6.1), profiles `local,compose` (`application-compose.yml` points the datasource,
  Liquibase and Quartz to Postgres). In the `springboot-start-app` directory, after the image is built:

  ```shell
  mvn install && smi-docker-build
  docker compose -f src/main/docker/docker-compose.yaml up -d
  docker compose -f src/main/docker/docker-compose.yaml logs -f springboot-start-app
  docker compose -f src/main/docker/docker-compose.yaml down
  ```

  Both compose files use the ports 11222 (Infinispan) and 8180 (Keycloak), so only one of them runs at a time.
  `src/main/docker/postgres-init.sql` and `keycloak-init.sql` are generated by `smi-postgresql-create-db-sql`, the
  command is in their first line: the database `springboot_start_app_local`, owned by the Liquibase user
  `localspringboot_start_appliquibase`, with the application user `localspringboot_start_app`, and `keycloak_local`
  the same way. The scripts run only on an empty volume: after a change of them,
  `docker compose -f src/main/docker/docker-compose.yaml down -v` removes the old databases first.

## RPM (LIVE and TEST machines)

```shell
make help
make rpms                                   # after mvn install
SMI_DEPLOY_TEST_HOSTS='test.example.com' make deploy-test
```

See [rpm.md](src/site/markdown/rpm.md).

## K8S, K3s

Local developer cluster, single machine K3s: `src/main/k8s/local`, namespace `microservice-local`. The same
components and values as `src/main/docker/docker-compose.yaml`:

| Component  | Files                                                         | Browser                              |
|------------|---------------------------------------------------------------|--------------------------------------|
| Postgres   | `postgres-*`, PostgreSQL 18 with PostGIS 3.6, init SQL of `src/main/docker/*.sql` | -                |
| Infinispan | `infinispan-*`, server and console only (the application has embedded Infinispan) | http://infinispan.localhost/console, microservice / g6p8 |
| Keycloak   | `keycloak-*`, database `keycloak_local`, realm import of `src/main/docker/keycloak/*.json` | http://keycloak.localhost/iam, admin / password |
| App        | `microservice-*`, profiles `local,k8s` (`application-k8s.yml`)    | http://microservice.localhost/calculator.html |

Postgres creates the databases and users from the init SQL once, on an empty volume, the same as docker compose.
Keycloak imports the realm once, on an empty database. Ingress is Traefik, the ingress controller of K3s. K3s runs the
images of its own containerd, not the ones of Docker: the application image is built with Docker and imported into
K3s (`imagePullPolicy: Never`). kubectl of K3s reads `/etc/rancher/k3s/k3s.yaml`, the context is `default`.

Once

```shell
# kubectl as the user, with its own config (or sudo k3s kubectl ...). Without KUBECONFIG the kubectl of K3s uses
# /etc/rancher/k3s/k3s.yaml of root: "open /etc/rancher/k3s/k3s.yaml.lock: permission denied"
mkdir -p ~/.kube && sudo cp /etc/rancher/k3s/k3s.yaml ~/.kube/config && sudo chown "$(id -u):$(id -g)" ~/.kube/config
chmod 600 ~/.kube/config
echo 'export KUBECONFIG=~/.kube/config' >> ~/.bashrc && export KUBECONFIG=~/.kube/config
# *.localhost: the browser resolves it to 127.0.0.1 itself and takes http://*.localhost as a secure context, which
# keycloak-js needs (Web Crypto API). Other host names would need HTTPS. curl and the rest of the machine:
echo "127.0.0.1 microservice.localhost keycloak.localhost infinispan.localhost" | sudo tee -a /etc/hosts

#Or
mkdir -p ~/.kube
sudo cp /etc/rancher/k3s/k3s.yaml ~/.kube/config
sudo chown "$(id -u):$(id -g)" ~/.kube/config
chmod 600 ~/.kube/config
export KUBECONFIG=~/.kube/config

kubectl config get-contexts        # should list "default" marked with *
kubectl config set-context --current --namespace=microservice-local
kubectl get nodes
```

Image into K3s, after every new build

```shell
mvn clean install && smi-docker-build
docker save setmyinfo/springboot-start-app:latest | sudo /usr/local/bin/k3s ctr images import -
sudo /usr/local/bin/k3s crictl images | grep springboot-start-app
```

Apply, in this order

```shell
K8S=./src/main/k8s/local
# 1. Namespace
kubectl apply -f ${K8S}/microservice-namespace.yaml
kubectl config set-context --current --namespace=microservice-local
# 2. Postgres. The init SQL comes from the files of docker compose (create or update the ConfigMap)
kubectl create configmap postgres-init-config-map \
    --from-file=./src/main/docker/keycloak-init.sql \
    --from-file=./src/main/docker/postgres-init.sql \
    --from-file=./src/main/docker/postgres-postgis.sql \
    --dry-run=client -o yaml | kubectl apply -f -
kubectl apply -f ${K8S}/postgres-secrets-map.yaml
kubectl apply -f ${K8S}/postgres-persistent-volume-claim.yaml
kubectl apply -f ${K8S}/postgres-deployment.yaml
kubectl apply -f ${K8S}/postgres-service.yaml
kubectl rollout status deployment postgres-deployment
# 3. Infinispan
kubectl apply -f ${K8S}/infinispan-secrets-map.yaml
kubectl apply -f ${K8S}/infinispan-persistent-volume-claim.yaml
kubectl apply -f ${K8S}/infinispan-deployment.yaml
kubectl apply -f ${K8S}/infinispan-service.yaml
kubectl apply -f ${K8S}/infinispan-ingress.yaml
kubectl rollout status deployment infinispan-deployment
# 4. Keycloak. The realm JSON comes from the files of docker compose (create or update the ConfigMap)
kubectl create configmap keycloak-realm-config-map \
    --from-file=./src/main/docker/keycloak/example-realm.json \
    --dry-run=client -o yaml | kubectl apply -f -
kubectl apply -f ${K8S}/keycloak-config-map.yaml
kubectl apply -f ${K8S}/keycloak-secrets-map.yaml
kubectl apply -f ${K8S}/keycloak-deployment.yaml
kubectl apply -f ${K8S}/keycloak-service.yaml
kubectl apply -f ${K8S}/keycloak-ingress.yaml
kubectl rollout status deployment keycloak-deployment --timeout=5m
# 5. App
kubectl apply -f ${K8S}/microservice-config-map.yaml
kubectl apply -f ${K8S}/microservice-secrets-map.yaml
kubectl apply -f ${K8S}/microservice-deployment.yaml
kubectl apply -f ${K8S}/microservice-service.yaml
kubectl apply -f ${K8S}/microservice-ingress.yaml
kubectl rollout status deployment microservice-deployment --timeout=5m
# NFS volume, optional: the mount of the deployment is commented out, the node needs nfs-utils
#kubectl apply -f ${K8S}/microservice-nfs-persistent-volume.yaml
#kubectl apply -f ${K8S}/microservice-nfs-persistent-volume-claim.yaml
```

```shell
curl http://microservice.localhost/actuator/health
curl http://keycloak.localhost/iam/realms/example/.well-known/openid-configuration
```

Login in http://microservice.localhost/calculator.html with the test users of the realm JSON (`calculator-user`,
`calculator-admin`, see [keycloak.md](src/site/markdown/keycloak.md)). Without the realm import:
`./src/main/keycloak/keycloak-setup.sh k8s`.

Postgres in Kubernetes. Users and passwords of `src/main/docker/*.sql` (smi-postgresql-create-db-sql), all `g6p8`:

| User                                 | Database                     | Use                                  |
|--------------------------------------|------------------------------|--------------------------------------|
| `postgres`                           | all                          | superuser of the image               |
| `localspringboot_start_app`          | `springboot_start_app_local` | application user (datasource, Quartz) |
| `localspringboot_start_appliquibase` | `springboot_start_app_local` | owner (Liquibase, tables)            |
| `localkeycloakliquibase`             | `keycloak_local`             | owner of the Keycloak tables         |

psql inside the pod, no client on the machine needed:

```shell
kubectl exec -it deployment/postgres-deployment -- psql -U postgres -d springboot_start_app_local
kubectl exec -it deployment/postgres-deployment -- psql -U localspringboot_start_app -h localhost -d springboot_start_app_local
# One command, for example the tables of the application
kubectl exec deployment/postgres-deployment -- psql -U postgres -d springboot_start_app_local -c '\dt'
# SQL file of the machine
kubectl exec -i deployment/postgres-deployment -- psql -U postgres -d springboot_start_app_local < file.sql
```

From the machine (psql, IntelliJ, DBeaver, ...): forward a local port to the service while the command runs. Port
5433, the same as docker compose, so a running compose Postgres must be stopped first, or take another port.

```shell
kubectl port-forward service/postgres-service 5433:5432
# In another terminal
psql -h localhost -p 5433 -U localspringboot_start_app -d springboot_start_app_local
```

JDBC URL of the tools: `jdbc:postgresql://localhost:5433/springboot_start_app_local`. Inside the cluster (other
pods): `postgres-service:5432`, from another namespace `postgres-service.microservice-local.svc.cluster.local:5432`.

Backup and restore

```shell
kubectl exec deployment/postgres-deployment -- pg_dump -U postgres -d springboot_start_app_local > springboot_start_app_local.sql
kubectl exec -i deployment/postgres-deployment -- psql -U postgres -d springboot_start_app_local < springboot_start_app_local.sql
```

Changed ConfigMap or Secret: apply it again and restart the deployment, the environment variables are read at the
start of the pod only.

```shell
kubectl apply -f ./src/main/k8s/local/microservice-config-map.yaml
kubectl rollout restart deployment microservice-deployment
```

Changed init SQL or realm JSON: they are used on an empty database only. Delete the Postgres volume (everything of
Postgres and Keycloak is lost) and apply again.

```shell
kubectl delete deployment keycloak-deployment microservice-deployment postgres-deployment
kubectl delete pvc postgres-persistent-volume-claim
```

New image, same tag: import it again and restart the pods.

```shell
docker save setmyinfo/springboot-start-app:latest | sudo k3s ctr images import -
kubectl rollout restart deployment microservice-deployment
kubectl rollout status deployment microservice-deployment
```

Check

```shell
kubectl config view
kubectl get namespaces
kubectl get all
kubectl get pvc
kubectl describe configmaps microservice-config-map
kubectl describe secret microservice-secrets-map
kubectl get pods
kubectl describe pod -l app=microservice-deployment
kubectl logs -l app=microservice-deployment --all-containers=true --follow
kubectl logs -l app=postgres-deployment
kubectl logs -l app=keycloak-deployment --follow
kubectl logs -l app=infinispan-deployment
kubectl exec -it deployment/postgres-deployment -- psql -U postgres -d springboot_start_app_local
kubectl get endpoints
kubectl get ingress
kubectl logs -n kube-system -l app.kubernetes.io/name=traefik -f
kubectl port-forward service/microservice-service 8082:80
kubectl port-forward service/postgres-service 5433:5432
```

Delete: the namespace with everything in it, the volumes included

```shell
kubectl delete namespace microservice-local
#kubectl delete -f ./src/main/k8s/local/microservice-nfs-persistent-volume.yaml
sudo k3s ctr images rm docker.io/setmyinfo/springboot-start-app:latest
```

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
6. mvn clean && mvn test && mvn verify && mvn install -Pe2e && mvn org.pitest:pitest-maven:mutationCoverage && mvn site:site

1. http://localhost:8082/
2. http://localhost:8082/home
3. http://localhost:8082/login
3. http://localhost:8082/h2-console
4. http://localhost:8082/listExample
5. http://localhost:8082/example.html
5. http://localhost:8082/calculator.html (Vue SPA with Keycloak login, see [keycloak.md](src/site/markdown/keycloak.md))
6. http://localhost:8082/graphiql?path=/graphql
7. http://localhost:8082/v3/api-docs
8. http://localhost:8082/swagger-ui/index.html
9. curl -X GET -H "Content-Type: application/json" -H "Accept: application/json" -i http://localhost:8082/api/example
10. http://localhost:8082/graphql
11. curl -X POST -H "Content-Type: application/json" -H "Accept: application/json" -i http://localhost:8082/graphql
    -d '{"query": "{ bookById(id: \"book-1\") { id name pageCount author { id firstName lastName } } }"}'
12. http://localhost:8082/v3/api-docs.yaml
13. http://localhost:8082/actuator
13. http://localhost:8082/actuator/info
14. http://localhost:8082/actuator/health
