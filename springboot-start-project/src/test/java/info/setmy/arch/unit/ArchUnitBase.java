package info.setmy.arch.unit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.BeforeEach;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static info.setmy.arch.unit.PackageGroups.MICROSERVICE_PACKAGES;

public abstract class ArchUnitBase {

    protected String currentClassName;

    protected JavaClasses importedClasses;

    protected ArchRule rule;

    @BeforeEach
    void setUp() {
        currentClassName = getClassPackageNameForAnalysis();
        importedClasses = new ClassFileImporter()
            //.withImportOption(DO_NOT_INCLUDE_TESTS)
            .importPackages(MICROSERVICE_PACKAGES);
    }

    protected String getClassPackageNameForAnalysis() {
        return appendSuffix(// TODO : remove, because simplifying package passing by users
            getClass().getPackage().getName()
        );
    }

    protected void prepareDefaultRule(final PackageDependencies packageDependencies) {
        rule = toDefaultRule(packageDependencies);
    }

    protected ArchRule toDefaultRule(final PackageDependencies packageDependencies) {
        ArchRule archRule = noClasses()
            .that().resideInAPackage(packageDependencies.packageName() + "..")
            .should().dependOnClassesThat()
            .resideOutsideOfPackages(appendSuffix(packageDependencies.allowedOnlyDependencies()));
        return archRule;
    }

    protected String[] appendSuffix(final String[] strings) {
        final String[] newStrings = new String[strings.length];
        for (int i = 0; i < strings.length; i++) {
            newStrings[i] = appendSuffix(strings[i]);
        }
        return newStrings;
    }

    protected String appendSuffix(final String string) {
        return string + "..";
    }
}
