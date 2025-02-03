package info.setmy.arch.unit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static info.setmy.arch.unit.PackageGroups.JAVA_PACKAGES;
import static info.setmy.arch.unit.PackageGroups.mergePackages;

class ArchUnitTest {

    JavaClasses importedClasses;
    ArchRule rule;

    @BeforeEach
    void setUp() {
        importedClasses = new ClassFileImporter().importPackages("info.setmy.arch.unit.example");
    }

    @Test
    void packageA_should_only_depend_on_java() {
        rule = noClasses()
            .that().resideInAPackage("info.setmy.arch.unit.example.a..")
            .should().dependOnClassesThat()
            .resideOutsideOfPackages(JAVA_PACKAGES);

        rule.check(importedClasses);
    }

    @Test
    void packageB_should_only_depend_on_packageA_and_java() {
        rule = classes()
            .that().resideInAPackage("info.setmy.arch.unit.example.b..")
            .should().onlyDependOnClassesThat()
            .resideInAnyPackage(mergePackages(
                    new String[]{
                        "info.setmy.arch.unit.example.a..",
                        "info.setmy.arch.unit.example.c.."
                    },
                    JAVA_PACKAGES
                )
            );

        rule.check(importedClasses);
    }

    @Test
    void packageC_should_only_depend_on_packageA_and_java() {
        rule = classes()
            .that().resideInAPackage("info.setmy.arch.unit.example.c..")
            .should().onlyDependOnClassesThat()
            .resideInAnyPackage(
                "info.setmy.arch.unit.example.a..",
                "java..",
                "javax.."
            );

        rule.check(importedClasses);
    }
}
