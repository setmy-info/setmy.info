package info.setmy.arch.unit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static info.setmy.arch.unit.PackageGroups.JAVA_PACKAGES;
import static info.setmy.arch.unit.PackageGroups.mergePackages;
import static info.setmy.arch.unit.PackageGroups.toArray;

class ArchUnitTest extends ArchUnitBase {

    @Test
    @DisplayName("Package A should only depend on Java")
    void packageA() {
        rule = noClasses()
            .that().resideInAPackage("info.setmy.arch.unit.example.a..")
            .should().dependOnClassesThat()
            .resideOutsideOfPackages(JAVA_PACKAGES);

        rule.check(importedClasses);

        // Same as "Elegant objects" way:
        PackageGroup mergedPackages = new MergedPackages(
            new JavaPackages()
        );
        String[] packages = mergedPackages.packages().toArray(new String[0]);
        rule = noClasses()
            .that().resideInAPackage("info.setmy.arch.unit.example.a..")
            .should().dependOnClassesThat()
            .resideOutsideOfPackages(packages);

        rule.check(importedClasses);

        /*
        // Same as shortened dependency check
        var packageName = "info.setmy.arch.unit.example.a";
        var packageDependencies = new PackageDependencies(
            packageName,
            mergePackages(
                toArray(packageName),
                JAVA_PACKAGES
            )
        );
        prepareDefaultRule(packageDependencies);

        rule.check(importedClasses);
        */
    }

    @Test
    @DisplayName("Package B should only depend on Package A and C and Java")
    void packageB() {
        rule = classes()
            .that().resideInAPackage(appendSuffix("info.setmy.arch.unit.example.b"))
            .should().onlyDependOnClassesThat()
            .resideInAnyPackage(
                mergePackages(
                    new String[]{
                        appendSuffix("info.setmy.arch.unit.example.a"),
                        appendSuffix("info.setmy.arch.unit.example.c")
                    },
                    JAVA_PACKAGES
                )
            );

        rule.check(importedClasses);

        // Same as "Elegant objects" way:
        PackageGroup mergedPackages = new MergedPackages(
            new GenericPackageGroup(
                appendSuffix("info.setmy.arch.unit.example.a"),
                appendSuffix("info.setmy.arch.unit.example.c")
            ),
            new JavaPackages()
        );
        String[] packages = mergedPackages.packages().toArray(new String[0]);
        rule = classes()
            .that().resideInAPackage(appendSuffix("info.setmy.arch.unit.example.b"))
            .should().onlyDependOnClassesThat()
            .resideInAnyPackage(packages);

        rule.check(importedClasses);
    }

    @Test
    @DisplayName("Package C should only depend on Package A and Java")
    void packageC_should_only_depend_on_packageA_and_java() {
        rule = classes()
            .that().resideInAPackage("info.setmy.arch.unit.example.c..")
            .should().onlyDependOnClassesThat()
            .resideInAnyPackage(
                mergePackages(
                    toArray(
                        appendSuffix("info.setmy.arch.unit.example.a")
                    ),
                    JAVA_PACKAGES
                )
            );

        rule.check(importedClasses);

        // Same as "Elegant objects" way:
        rule = classes()
            .that().resideInAPackage(appendSuffix("info.setmy.arch.unit.example.b"))
            .should().onlyDependOnClassesThat()
            .resideInAnyPackage(
                new MergedPackages(
                    new SinglePackageGroup(
                        appendSuffix("info.setmy.arch.unit.example.a")
                    ),
                    new JavaPackages()
                ).packages().toArray(new String[0])
            );

        rule.check(importedClasses);
    }
}
