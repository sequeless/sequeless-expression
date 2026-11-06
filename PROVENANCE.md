# Provenance

- Created from: sequeless/sequeless-template at commit 916aa01 (Initial commit). The template's init script was not run. Its template-only files (`template-core/`, `scripts/init-repo.sh`, `docs/specs/stage-0-sequeless-template.md`, `.github/workflows/template-smoke.yml`) were removed by hand.
- Source repo: sequeless/sequeless-prototype
- Source SHA at the move: fda7c3e (main, after the javadoc prerequisite PR #82, which is squash-merged on top of `d53d3f1`, main after spec 7, PR #80).
- Source paths, moved to `expression-api` (package `org.sequeless.expression.api`):
  - `sequeless-spi/src/main/java/org/sequeless/spi/expression/{ExpressionPort,ExpressionContext,ExpressionException,package-info}.java`
- Source paths, moved to `expression-jexl-adapter` (package `org.sequeless.expression.jexl`):
  - `sequeless-adapter-expression-jexl/src/main/java/org/sequeless/adapter/expression/jexl/{JexlExpressionPort,package-info}.java`
  - test `sequeless-adapter-expression-jexl/src/test/java/org/sequeless/adapter/expression/jexl/JexlExpressionPortContractTest.java`
- Source paths, moved to `expression-testkit` (package `org.sequeless.expression.testkit`):
  - `sequeless-spi-testkit/src/main/java/org/sequeless/testkit/expression/{ExpressionContract,package-info}.java`
- Source paths, moved to `expression-spring-boot-autoconfig` (package `org.sequeless.expression.autoconfigure`):
  - `sequeless-adapter-expression-jexl/src/main/java/org/sequeless/adapter/expression/jexl/JexlExpressionAutoConfiguration.java`
  - `sequeless-adapter-expression-jexl/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`
  - test `sequeless-adapter-expression-jexl/src/test/java/org/sequeless/adapter/expression/jexl/JexlExpressionAutoConfigurationTest.java`
- The move: packages renamed in place (`org.sequeless.spi.expression` to `org.sequeless.expression.api`, `org.sequeless.adapter.expression.jexl` to `org.sequeless.expression.jexl`, `org.sequeless.testkit.expression` to `org.sequeless.expression.testkit`, and the autoconfig class to `org.sequeless.expression.autoconfigure`). The autoconfig is split into its own module, per the owner's decision on the module layout (spec 8, section 5).
- Not moved: nothing in the prototype's expression consumers. The prototype keeps its copies until spec 9 deletes them.
- Date: 2026-11-06
