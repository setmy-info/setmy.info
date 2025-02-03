package info.setmy.arch.unit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchUnitTest {

    JavaClasses importedClasses;

    @BeforeEach
    void setUp() {
        importedClasses = new ClassFileImporter().importPackages("info.setmy.arch.unit.example");
    }

    @Test
    void packageA_should_only_depend_on_java() {
        ArchRule rule = noClasses()
            .that().resideInAPackage("info.setmy.arch.unit.example.a..")
            .should().dependOnClassesThat()
            .resideOutsideOfPackages(
                "java..",
                "javax.."
            );

        rule.check(importedClasses);
    }

    @Test
    void packageB_should_only_depend_on_packageA_and_java() {
        ArchRule rule = classes()
            .that().resideInAPackage("info.setmy.arch.unit.example.b..")
            .should().onlyDependOnClassesThat()
            .resideInAnyPackage(
                "info.setmy.arch.unit.example.a..",
                "java..",
                "javax.."
            );

        rule.check(importedClasses);
    }
}
