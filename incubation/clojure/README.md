# Getting Started

```shell
.\mvnw clean install
```

```shell
./mvnw clean install
```

### Execution

```shell
.\mvnw spring-boot:run -Dspring-boot.run.arguments="-o param"
```

```shell
./mvnw spring-boot:run -Dspring-boot.run.arguments="-o param"
```

```shell
java -jar .\target\clojure-0.0.0-SNAPSHOT.jar -o param
```

#### Clojure run

```shell
.\mvnw clojure:run
```

```shell
./mvnw clojure:run
```

# Leiningen

[Leiningen](https://leiningen.org/)

[Lein tutorial](https://codeberg.org/leiningen/leiningen/src/branch/stable/doc/TUTORIAL.md)

## How it's done

```shell
mkdir leiningen
cd leiningen
lein new app tutorial
cd tutorial
lein run
lein test
lein uberjar
java -jar ".\target\default+uberjar\tutorial-0.1.0-SNAPSHOT-standalone.jar" fff
```
