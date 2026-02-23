# java-stealer Implementation Report

## Summary

Analysis and implementation report for the `java-stealer` library module within the `setmy.info` monorepo.

## Implemented Steps

The `StealerService.steal()` pipeline executes these steps in order for each repository:

| Step | Status | Notes |
|------|--------|-------|
| 1. Clone & Checkout | Already implemented | Clones repo, checks out branch if specified |
| 2. Copy | Already implemented | Copies full repo or subdirectory to copy dir |
| 3. Cleanup | Already implemented | Deletes specified files/dirs from copy dir |
| 4. Patch | **Implemented** | Applies unified diff patches via external `patch` command |
| 5. Change | **Implemented** | Regex-based text replacement across all files in copy dir |
| 6. Finalization | **Implemented** | Copies each step's copy dir to `.stealer/final/` consolidation directory |
| 7. Deploy | **Implemented** | Copies `.stealer/final/` to working directory once |

## Changes Made

### Models
- **`StepConfig`**: Added `patches: List<String>` and `changes: List<Change>` fields.
- **`StepInnerConfig`**: Added `patches` and `changes` fields with null-safe helpers (`havePatches()`, `getPatches()`, `haveChanges()`, `getChanges()`).
- **`Change`** (new): Immutable model with `pattern` (regex) and `replacement` fields.

### Mappers
- **`StepConfigMapper`**: Maps `patches` and `changes` from `StepConfig` to `StepInnerConfig`.
- **`StealerConfigMapper`** (CLI): Maps `patches` and `changes` from YAML. Each YAML `change` entry has `pattern` and `replacement` keys.

### Service
- **`StealerService.doStepCopyForSubDirectory()`**: Fixed `.git` directory being copied — now uses `FileFilter` to exclude `.git` at all directory levels.
- **`StealerService.doStepPatch()`**: Implemented using `Executor` to run `patch -p0 -i <file>` in the copy directory.
- **`StealerService.doStepChange()`**: Implemented — walks all files in copy dir recursively, applies `String.replaceAll(pattern, replacement)` using UTF-8 encoding. Only writes back files whose content changed.
- **`StealerService.doStepFinalization()`**: Implemented — copies each step's copy directory into the working directory root.

### Unit Tests (20 passing)
- **`ChangeTest`** (`Test.java`): Tests `Change` builder and `toBuilder()`.
- **`StepConfigMapperTest`** (`Test.java`): Tests mapping with all fields (patches, changes), null handling.
- **`StealerConfigMapperTest`** (`Test.java`): Tests YAML map → `StepConfig` with changes, missing optional fields, non-map input.
- **`StepInnerConfigTest`** (`Test.java`): Tests all helper methods including `haveChanges()`, `getChanges()`.

### Integration Test Updates
- **`StealerServiceIT.testStealerServiceWithChange()`**: Clones test-a, applies a `Change` (replaces text in `test.txt`), asserts the result. Runs on all platforms.
- **`StealerServiceIT.testStealerServiceWithPatch()`**: Linux-only (`@EnabledOnOs(OS.LINUX)`), applies a patch file to `test.txt`, asserts content.
- **`src/test/resources/patches/stealer-test-a.patch`**: Unified diff patch for `test.txt`.

### Test Resources
- **`src/test/resources/StealerCallableIT/.stealer/config.yaml`**: YAML config for the `StealerCallableIT` integration test.

## Directory Structure

```
.stealer/
  clone/           <- Immutable: original git clones per repo
    stealer-test-a/
    stealer-test-b/
  copy/            <- Working: copied (and subdirectory-filtered) content
    stealer-test-a/ <- after cleanup and patch applied
    stealer-test-b/
```

After finalization, content from `copy/<repo>/` is merged into the working directory root.

## Known Limitations

### Patch Command Availability
- Patching requires the `patch` binary available in `PATH`.
- Linux (Rocky/Fedora): `patch` is typically installed system-wide.
- Windows: requires MSYS2/Git bash tools, or `C:\sources\utils\bin\patch.exe` in PATH.
- Patch IT tests are annotated `@EnabledOnOs(OS.LINUX)` to run only on Linux CI environments.

### doChange Applied to All Files
- `doStepChange` applies text replacement to every regular file in the copy directory, including potential binary files. If a regex pattern accidentally matches binary content, the file could be corrupted. Suggestion: limit application by file extension, or let the caller take responsibility for targeted patterns.

## Resolved Issues

### Vcs Dead Code Removed
- Removed the dead `stepInnerConfig.toBuilder().vcs(vcs).build()` call in `doStepCloneAndCheckout`. The result was discarded; `vcs` local variable is still used correctly.

### StepConfig.stepConfigs Immutability
- `StealerConfig.stepConfigs` now uses Lombok `@Singular` annotation. The list is immutable after construction.
- `StealerConfigService.getConfig()` refactored to build the `List<StepConfig>` first, then pass it to the builder via `.stepConfigs(stepConfigs)`.
- All IT tests updated to use `.stepConfig(one)` builder method instead of `getStepConfigs().add()`.

### Final Directory Consolidation Implemented
- `doStepFinalization()` now copies each step's copy directory into `.stealer/final/` (the consolidation directory).
- New `doDeployToWorkingDirectory()` step copies `.stealer/final/` into the working directory once, after all steps are finalized.
- `StealerService.FINAL_DIR = "final"` constant added.
- `init()` wires `finalDirectory = new File(stealerDirectory, "final")`.
- `createDirectories()` now creates the final directory.
- IT tests updated with `FINAL_DIR` constant, `setUp` cleans it, and working-directory assertions added.

### Clones Directory Naming
- Renamed `StealerService.CLONE_DIR` from `"clone"` to `"clones"` (plural) to match backlog specification.
- All IT test constants (`CLONE_DIR`, `A_CLONE_DIR`, `B_CLONE_DIR`) updated from `/clone/` to `/clones/`.

---

# JasperReports 7.x Migration Report

## Summary

Upgraded JasperReports XML report definitions (JRXML files) in the `java-reports` module from the pre-7.x format to the JasperReports 7.x Jackson-based XML format. The `JasperReportsServiceIT` integration test now passes.

## Versions

- JasperReports: `7.0.3` (already set in `dependencies-bom` at `<jasperreports.version>`)
- Dependencies already in BOM and `java-reports/pom.xml`: `jasperreports`, `jasperreports-fonts`, `jasperreports-pdf`, `itextpdf`

## JRXML Format Changes (pre-7.x → 7.x)

JasperReports 7.x replaced the Digester-based XML parser with a Jackson-based XML deserializer. This required the following JRXML format changes:

### 1. Band elements wrapped in `<element kind="...">` containers

**Before (pre-7.x):**
```xml
<title height="79" splitType="Stretch">
    <textField>
        <reportElement x="180" y="30" width="200" height="21" uuid="..."/>
        <textFieldExpression><![CDATA[$F{title}]]></textFieldExpression>
    </textField>
</title>
```

**After (7.x):**
```xml
<title height="79" splitType="Stretch">
    <element kind="textField" x="180" y="30" width="200" height="21" uuid="...">
        <expression><![CDATA[$F{title}]]></expression>
    </element>
</title>
```

### 2. `<reportElement>` removed — position/size attributes moved to `<element>`

The `<reportElement>` wrapper element is no longer used. Attributes like `x`, `y`, `width`, `height`, `uuid`, `positionType`, `stretchType` are now directly on the `<element>` tag.

### 3. Polymorphic type discriminator `kind` attribute required

Each element in a band list requires a `kind` attribute telling Jackson which concrete type to instantiate. Known `kind` values: `textField`, `staticText`, `image`, `subreport`, `rectangle`, `ellipse`, `line`, `frame`, `crosstab`, `chart`, `break`, `componentElement`, `genericElement`.

### 4. Element expression properties renamed to `<expression>`

| Pre-7.x element name     | 7.x element name |
|--------------------------|------------------|
| `<textFieldExpression>`  | `<expression>`   |
| `<subreportExpression>`  | `<expression>`   |

`<dataSourceExpression>` for subreports remains unchanged.

## Files Changed

- `java-reports/src/test/resources/reports/test.jrxml` — main report with title text field and detail subreport
- `java-reports/src/test/resources/reports/sub.jrxml` — subreport with a text field
- `java-reports/src/test/java/info/setmy/reports/JasperReportsServiceIT.java` — extended `testExport()` with Tika-based PDF content verification

## Tika Content Verification

After PDF export, Apache Tika parses the generated PDF and asserts the report contains the expected model data:

```java
final String content = new Tika().parseToString(child);
assertThat(content)
    .contains(model.getTitle())         // "Jasper reports example"
    .contains(subReportModel.getSubData()); // "Sub report data!"
```
