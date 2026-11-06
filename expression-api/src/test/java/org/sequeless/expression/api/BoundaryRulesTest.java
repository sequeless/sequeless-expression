package org.sequeless.expression.api;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

/**
 * Mechanically enforced boundaries for expression-api (spec 8, section 5). It depends on the JDK, kernel-values and itself only, and never on Spring.
 */
@AnalyzeClasses(
        packages = "org.sequeless.expression.api",
        importOptions = {ImportOption.DoNotIncludeTests.class})
class BoundaryRulesTest {

    @ArchTest
    static final ArchRule module_is_free_of_spring = noClasses()
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("org.springframework..")
            .as("the expression API must stay transport-agnostic: no Spring dependency");

    @ArchTest
    static final ArchRule module_depends_only_on_java_and_its_kernel_values = noClasses()
            .should()
            .dependOnClassesThat()
            .resideOutsideOfPackages("java..", "org.sequeless.expression.api..", "org.sequeless.kernel.values..")
            .as("expression-api depends on the JDK, itself and kernel-values only");
}
