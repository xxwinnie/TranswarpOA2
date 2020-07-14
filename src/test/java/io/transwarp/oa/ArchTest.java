package io.transwarp.oa;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("io.transwarp.oa");

        noClasses()
            .that()
            .resideInAnyPackage("io.transwarp.oa.service..")
            .or()
            .resideInAnyPackage("io.transwarp.oa.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..io.transwarp.oa.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
