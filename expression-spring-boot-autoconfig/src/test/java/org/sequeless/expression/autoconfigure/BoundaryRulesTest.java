package org.sequeless.expression.autoconfigure;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

/**
 * Mechanically enforced boundaries for expression-spring-boot-autoconfig (spec 8, section 5). This is the only module that may depend on Spring.
 */
@AnalyzeClasses(
        packages = "org.sequeless.expression.autoconfigure",
        importOptions = {ImportOption.DoNotIncludeTests.class})
class BoundaryRulesTest {

    @ArchTest
    static final ArchRule module_depends_only_on_spring_and_the_expression_modules = noClasses()
            .should()
            .dependOnClassesThat()
            .resideOutsideOfPackages(
                    "java..", "org.springframework..", "org.sequeless.expression..", "org.sequeless.kernel..")
            .as("the autoconfiguration depends on Spring, the expression modules and kernel types only");
}
