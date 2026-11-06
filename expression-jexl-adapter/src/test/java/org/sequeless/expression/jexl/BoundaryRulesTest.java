package org.sequeless.expression.jexl;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

/**
 * Mechanically enforced boundaries for expression-jexl-adapter (spec 8, section 5). It is the only module that may touch JEXL, and it never depends on Spring.
 */
@AnalyzeClasses(
        packages = "org.sequeless.expression.jexl",
        importOptions = {ImportOption.DoNotIncludeTests.class})
class BoundaryRulesTest {

    @ArchTest
    static final ArchRule module_is_free_of_spring = noClasses()
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("org.springframework..")
            .as("the JEXL adapter must stay transport-agnostic: no Spring dependency");

    @ArchTest
    static final ArchRule module_depends_only_on_its_declared_dependencies = noClasses()
            .should()
            .dependOnClassesThat()
            .resideOutsideOfPackages(
                    "java..",
                    "org.apache.commons.jexl3..",
                    "org.sequeless.expression.api..",
                    "org.sequeless.expression.jexl..",
                    "org.sequeless.kernel.values..")
            .as("expression-jexl-adapter depends on the JDK, JEXL, the API, kernel-values and itself only");
}
