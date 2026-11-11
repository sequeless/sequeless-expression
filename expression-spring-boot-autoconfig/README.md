# expression-spring-boot-autoconfig

Spring Boot autoconfiguration that registers a `JexlExpressionPort` as the `ExpressionPort` bean when
`sequeless.expression.adapter=jexl`.

- Coordinates: `org.sequeless:expression-spring-boot-autoconfig`, latest release.
- Built against Spring Boot 4.1.1. Other versions are not tested.
- Setup, and what happens when an application defines its own port:
  [enable the JEXL adapter](../docs/how-to/enable-the-jexl-adapter.md).
