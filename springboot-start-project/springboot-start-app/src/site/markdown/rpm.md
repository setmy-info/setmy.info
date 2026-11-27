# RPM packaging and deployment

springboot-start-app is packaged as noarch RPMs the same way as the other setmy.info projects (smi-*-config):
a `Makefile` that calls `rpm/Makefile`, one spec per environment, and `smi-deploy` of setmy-info-scripts for the
upload and the hand-over to the deployment service of the server.

| Package                         | Machine                    | Spring profile |
|---------------------------------|----------------------------|----------------|
| `smi-springboot-start-app`      | LIVE (BE machine)          | `live`         |
| `smi-springboot-start-app-test` | TEST (FE and BE on one host) | `test`       |

The two packages conflict, a machine has one of them. They carry the same jar.

## What is installed

| Path                                               | What                                                     |
|----------------------------------------------------|----------------------------------------------------------|
| `/opt/setmy.info/lib/springboot-start-app.jar`     | the executable jar (`target/springboot-start-app-<version>-exec.jar`) |
| `/opt/setmy.info/etc/systemd/system/springboot-start-app.service` | the service                         |
| `/etc/systemd/system/springboot-start-app.service` | symlink to it, as setmy-info-elixir does         |
| `/var/opt/setmy.info/microservice`                 | working and log directory, `microservice:microservice` 0750, made by `%post` |

Required on the machine, not packaged here:

* `setmy-info-scripts`: `/opt/setmy.info/bin/smi-spring-boot` starts the jar, the `microservice` user
  (the `%pre` scriptlet creates it too when missing), the deployment service.
* `/opt/jdk`: the JDK, installed by hand, a symlink to the JDK in use. The unit puts `/opt/jdk/bin` first in `PATH`.

Not in the RPM on purpose: the machine configuration. It comes from the separate (private) configuration
repository and its packages, `smi-springboot-start-app-config` (LIVE) and `smi-springboot-start-app-config-test`
(TEST), all under `/opt/setmy.info/etc/springboot-start-app/`, each file optional, read in this order:

* `springboot-start-app.env`: every machine (`SMI_IAM_*`, database, `JDK_JAVA_OPTIONS` for JVM flags)
* `machine.env`: this environment
* `local.env`: made by hand on the machine, in no package, for secrets
* `springboot-start-app.yaml`: extra Spring configuration, loaded with `spring.config.additional-location`
* `logback.xml`: made by hand when needed, a logging configuration instead of the one in the jar, with
  `MICROSERVICE_LOG_CONFIG_FILE_PATH=/opt/setmy.info/etc/springboot-start-app/logback.xml` in `local.env`

The log file is `/var/opt/setmy.info/microservice/springboot-start-app.log`, rolled over daily or at 10 MB into
`springboot-start-app.log.<date>.<n>.gz` beside it.

Example variables in [`src/main/sh/etc/springboot-start-app/springboot-start-app.env`](../../../src/main/sh/etc/springboot-start-app/springboot-start-app.env).
The application package starts the service when `machine.env` is already there, otherwise the configuration
package starts it when it is installed.

The HTTP port is the default of `application.yaml`.

## Environment at install time

The unit carries one commented line per environment:

```
# NB! LIVE ONLY: Environment=MICROSERVICE_PROFILES=live
# NB! TEST ONLY: Environment=MICROSERVICE_PROFILES=test
```

The `%post` scriptlet of the installed package removes the prefix of its own environment, the same way as
smi-nftables-config does with `# NB! BE ONLY: `. One unit file in git, one active profile on the machine.

`%post` also enables the service and restarts it, so every deployed version is restarted. The unit counts as
started only when the application accepts connections on its port (`ExecStartPost`, at most `TimeoutStartSec`),
so a restart returns after Liquibase has finished, and a second restart in the same deployment (the configuration
package) never stops the application in the middle of a migration:

```shell
sudo systemctl start springboot-start-app
journalctl -u springboot-start-app -n 50
```

## Build

```shell
mvn clean install                                   # the jar, in the repository root or in this module
make -C springboot-start-project/springboot-start-app help
make -C springboot-start-project/springboot-start-app rpms
```

The version comes from `pom.xml`: `1.4.0` gives version `1.4.0` with release `0.<BUILD_NUMBER>` (Jenkins
build number), so every CI build is an upgrade of the previous one; a version without `-SNAPSHOT` gives release `1`.
Output in `$(rpm --eval '%{_topdir}')/RPMS/noarch`.

## Deploy

No server is named in this repository. The servers are environment variables, set in the Jenkins job (or node)
configuration, `[user@]host[:port]`, several separated by spaces:

| Variable                | Meaning                     |
|-------------------------|-----------------------------|
| `SMI_DEPLOY_TEST_HOSTS` | TEST machines               |
| `SMI_DEPLOY_LIVE_HOSTS` | LIVE BE machines            |
| `SMI_SPRINGBOOT_TEST_HOSTS`, `SMI_SPRINGBOOT_LIVE_HOSTS` | used when the two above are not set; the same names as the configuration repository, so one pair of job variables deploys the application and its configuration |
| `SMI_DEPLOY_USER`, `SMI_DEPLOY_PORT`, `SMI_DEPLOY_REMOTE_DIR`, `SMI_DEPLOY_INCOMING_DIR` | read by `smi-deploy`, defaults `deploy`, `22`, `/home/<user>/deploy`, `/var/opt/setmy.info/incoming` |

```shell
SMI_DEPLOY_TEST_HOSTS='test.example.com' make -C springboot-start-project/springboot-start-app deploy-test
SMI_DEPLOY_LIVE_HOSTS='be.example.com:2222' make -C springboot-start-project/springboot-start-app deploy-live
```

`deploy-*` is `upload-*` (scp to the upload directory, nothing installs yet) and `move-*` (atomic hand-over into the
incoming directory). On the server `setmy-info-deploy.path` starts `smi-incoming-deploy`, which runs
`rpm -U --replacepkgs --oldpackage`:

```shell
journalctl -u setmy-info-deploy.service -n 50
```

The root `Jenkinsfile` builds the RPMs after `mvn clean install` and deploys: `devel*`, `release*` and `hotfix*` branches to TEST,
`master` to LIVE.

Without a host variable in the job, the deploy stages only say so and the build stays green.

## Version

`smi-new-version` (setmy-info-scripts) bumps the version of every module at once, in the repository root:

```shell
smi-new-version 1.3.0-SNAPSHOT
```

It includes `./.new-version`, which includes `./src/main/sh/build/.new-version`, whose `doVersionChange` runs
`mvn versions:set` over all modules, fixes the `info.setmy` versions Maven left behind, and updates the version in
`tomcat-start-project` (Makefile, Dockerfile, Jailfile), `java-clojure` (project.clj, deps.edn), the fallback
`Version` of the specs here and this page. Files that are not there are skipped. `git new-version 1.3.0-SNAPSHOT`
does the same in a git flow feature branch, committed and ready to merge. The `%changelog` entry of the specs is
written by hand.
