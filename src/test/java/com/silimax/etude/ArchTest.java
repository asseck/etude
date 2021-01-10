package com.silimax.etude;

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
            .importPackages("com.silimax.etude");

        noClasses()
            .that()
            .resideInAnyPackage("com.silimax.etude.service..")
            .or()
            .resideInAnyPackage("com.silimax.etude.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.silimax.etude.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
