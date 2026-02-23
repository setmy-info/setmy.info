# java-stealer Implementation Report

## java-stealer module

- Module path: `java-stealer/`
- Main package: `info.setmy.stealer`
- CLI entry point: `info.setmy.stealer.cli.Application`
- CLI parameter model: `info.setmy.stealer.cli.models.StealerCallable` (picocli `Callable<Integer>`)
- Config is read from `.stealer/config.yaml` relative to `user.dir` at runtime
- Uses picocli for CLI parsing (no Spring Boot)
- Assembly distribution: `src/main/assembly/cli.xml` → `bin/stealer`, `bin/stealer.cmd`, `lib/*.jar`
- Shell scripts: `src/main/sh/stealer` (Linux/macOS), `src/main/cmd/stealer.cmd` (Windows)
- Integration tests use `target/test-classes/<TestClassName>` as the working directory
- Test resources under `src/test/resources/<TestClassName>/.stealer/config.yaml` are copied to the above directory by
  Maven

## Task 1: Integration Test for CLI Application

### What was implemented

Created `src/test/java/info/setmy/stealer/cli/ApplicationIT.java` — an integration test
for the CLI application layer that exercises the full picocli `CommandLine` stack.

- Tests via `CommandLine.execute(arguments)` (the proper picocli testing approach)
- Uses the same test data structure and assertions as `StealerCallableIT`
- Working directory is set to `target/test-classes/ApplicationIT` before each test
- Test resources at `src/test/resources/ApplicationIT/.stealer/config.yaml` provide the
  same two-step config (stealer-test-a on master, stealer-test-b on develop) as used by
  `StealerCallableIT`
- Cleans up clone, copy, and final directories in `@BeforeEach`
- Restores original `user.dir` in `@AfterEach`

### Differences from StealerCallableIT

| Aspect      | StealerCallableIT        | ApplicationIT                        |
|-------------|--------------------------|--------------------------------------|
| Entry point | `stealerCallable.call()` | `new CommandLine(...).execute(args)` |
| Tests       | Callable level           | Full picocli CommandLine level       |
| Exit code   | Not verified             | Verified to be zero                  |

### Note on Application.main()

`Application.main()` is declared as package-private (`static void main`) rather than
`public static void main`. In Java 25 the relaxed entry-point feature (JEP 445 lineage)
allows this for JVM launch, but the test uses `CommandLine` directly to avoid the
`System.exit()` call that `Application.main()` makes.

## Task 2: Shell and CMD Scripts

### Files created

- `src/main/sh/stealer` — POSIX shell script (executable, `0755`)
- `src/main/cmd/stealer.cmd` — Windows CMD script

Both scripts locate the `lib/` directory relative to themselves and launch the application
via the classpath (`-cp ../lib/*`), invoking `info.setmy.stealer.cli.Application` as the
entry point. Java must be on `PATH`.

## Task 3: Maven Assembly Plugin

### Assembly descriptor

Created `src/main/assembly/cli.xml` which produces a distributable `zip` and `tar.gz`
archive containing:

```
<artifactId>-<version>-cli/
  bin/
    stealer          (from src/main/sh, mode 0755)
    stealer.cmd      (from src/main/cmd, mode 0644)
  lib/
    java-stealer-<version>.jar
    <all runtime dependency JARs>
```

### pom.xml change

The previously commented-out `maven-assembly-plugin` block was enabled and updated:

- Version bumped from `3.1.0` to `3.7.1`
- Descriptor changed from `src/main/assembly/src.xml` to `src/main/assembly/cli.xml`
- Plugin runs in the `package` phase via the `make-assembly` execution

## Task 4: Fix Shell Script POSIX Compliance

`src/main/sh/stealer` was already POSIX compliant (`#!/bin/sh`, no bash-specific features,
standard `$()` command substitution, `exec`, and `"$@"`). Added `set -e` so the script
exits immediately on any error before launching Java.

The classpath wildcard `"${SCRIPT_DIR}/../lib/*"` is correctly quoted (double quotes prevent
shell glob-expansion; Java itself expands the `*` at runtime).

The assembly descriptor sets `<fileMode>0755</fileMode>` for the script, which applies the
executable bit in the distribution archive. When using the script directly from source on
Unix, run `chmod +x src/main/sh/stealer`.

## Task 5: ApplicationWithChangeIT — Change Step via CLI

### What was implemented

Created `src/test/java/info/setmy/stealer/cli/ApplicationWithChangeIT.java` — an
integration test that exercises the **Change step** (pattern/replacement text substitution)
through the full picocli `CommandLine` stack, mirroring
`StealerServiceIT.testStealerServiceWithChange()`.

- Working directory: `target/test-classes/ApplicationWithChangeIT`
- Config: `src/test/resources/ApplicationWithChangeIT/.stealer/config.yaml`
    - Clones `stealer-test-a` (master branch)
    - Applies change: `"this should be replaced"` → `"this has been changed"`
- Asserts `test.txt` in the copy directory contains the replacement text
- Asserts `test.txt` is deployed to the working directory

This proves that the Change step is accessible and exercised through the CLI layer, closing
the gap identified by `(state "Changes are not reflected up to CLI tool.")`.
