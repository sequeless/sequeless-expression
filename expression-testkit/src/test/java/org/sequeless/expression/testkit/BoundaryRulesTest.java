package org.sequeless.expression.testkit;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

/**
 * Mechanically enforced boundaries for expression-testkit (spec 8, section 5). The contract is adapter-agnostic: no Spring, no JEXL, no adapter module.
 */
@AnalyzeClasses(
        packages = "org.sequeless.expression.testkit",
        importOptions = {ImportOption.DoNotIncludeTests.class})
class BoundaryRulesTest {

    @ArchTest
    static final ArchRule module_is_free_of_spring = noClasses()
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("org.springframework..")
            .as("the testkit must stay transport-agnostic: no Spring dependency");

    @ArchTest
    static final ArchRule module_is_free_of_the_jexl_adapter = noClasses()
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("org.apache.commons.jexl3..", "org.sequeless.expression.jexl..")
            .as("the contract stays adapter-agnostic: no JEXL and no adapter dependency");
}
