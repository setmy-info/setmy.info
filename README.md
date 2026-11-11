# setmy.info

setmy.info

## How to upgrade

Dependency and plugin versions are kept as `<properties>` in two places:

* `dependencies-bom/pom.xml` – the BOM (standalone, no parent). Versions used in its `<dependencyManagement>`.
* `pom.xml` – the root/parent pom (`multi-projects`). Imports the BOM and has its own copy of version properties
  (mainly plugins).

All upgrades are done with the [versions-maven-plugin](https://www.mojohaus.org/versions/versions-maven-plugin/).
Use the Maven wrapper `./mvnw` (or `mvn`). The `-DgenerateBackupPoms=false` flag avoids `pom.xml.versionsBackup`
files; leave it out if you want backups and then use `versions:revert` / `versions:commit`.

### 1. See what can be upgraded

```shell
# BOM
./mvnw -f dependencies-bom/pom.xml versions:display-property-updates
./mvnw -f dependencies-bom/pom.xml versions:display-dependency-updates

# Root pom only (-N = non-recursive)
./mvnw -N versions:display-property-updates
./mvnw -N versions:display-plugin-updates
./mvnw -N versions:display-parent-updates

# Whole reactor (all modules)
./mvnw versions:display-dependency-updates versions:display-plugin-updates
```

Add `-DallowSnapshots=false` (default) and, to skip alpha/beta/RC/milestone versions, e.g.
`-Dmaven.version.ignore='(?i).*(alpha|beta|rc|cr|m)[.-]?[0-9]*.*'`.

### 2. Upgrade the BOM

```shell
./mvnw -f dependencies-bom/pom.xml versions:update-properties -DgenerateBackupPoms=false \
    -Dmaven.version.ignore='(?i).*(alpha|beta|rc|cr|m)[.-]?[0-9]*.*'
```

Useful options:

* `-DallowMajorUpdates=false` – stay within the current major (also `-DallowMinorUpdates=false` for patch only).
* `-DincludeProperties=jackson.version,spring.version` – upgrade only selected properties.
* `-DexcludeProperties=java.version` – leave selected properties untouched.

Install the BOM so the root pom (which imports it with `scope=import`) sees the new versions:

```shell
./mvnw -f dependencies-bom/pom.xml install
```

### 3. Upgrade the root pom

```shell
./mvnw -N versions:update-properties -DgenerateBackupPoms=false \
    -Dmaven.version.ignore='(?i).*(alpha|beta|rc|cr|m)[.-]?[0-9]*.*'
```

### 4. Upgrade versions written directly in modules (not via properties)

```shell
./mvnw versions:use-latest-releases -DgenerateBackupPoms=false   # hard-coded <version> in dependencies
./mvnw versions:update-parent       -DgenerateBackupPoms=false   # parent versions (e.g. external parents)
```

### 5. Keep the BOM and the root pom in sync

Both poms define the same properties. After upgrading, compare them and align the root pom values with the BOM:

```shell
diff <(grep -E '<[a-z0-9.]+(version|verion|plugin)>' dependencies-bom/pom.xml) \
     <(grep -E '<[a-z0-9.]+(version|verion|plugin)>' pom.xml)
```

### 6. Upgrade the Maven wrapper (optional)

```shell
./mvnw wrapper:wrapper -Dmaven=3.9.16
```

### 7. Upgrade the JDK in CI

`.github/workflows/pipeline.yml` – set `JAVA_VERSION` to the newest JDK
(check releases of `actions/checkout` and `actions/setup-java` too). Jenkins uses whatever is in `/opt/jdk`.
`java.version` in the poms is the compile target and can stay lower than the CI/build JDK.

### 8. Build, verify and commit

```shell
./mvnw clean install
git diff
```

Typical symptom of root pom and BOM out of sync: after a JDK upgrade the compile fails with
`Fatal error compiling: java.lang.ExceptionInInitializerError: com.sun.tools.javac.tree.EndPosTable` – the root
pom's `lombok.version` (used in `annotationProcessorPaths`) is too old for the JDK. Set it to the BOM's value.

If something breaks, pin that property back (or exclude it with `-DexcludeProperties=...`) and rerun.
When backups were generated, undo everything with `./mvnw versions:revert` or accept with `./mvnw versions:commit`.

### Changing the project version itself

To bump the project version (root, BOM and all modules at once):

```shell
./mvnw versions:set -DnewVersion=1.3.0-SNAPSHOT -DprocessAllModules=true -DgenerateBackupPoms=false
```
