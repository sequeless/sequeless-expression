# sequeless-expression

Sandboxed expression evaluation behind the `ExpressionPort` SPI: the guards on state machine
transitions, `sq:value`/`sq:expression` on SetProperty and CreateObject, and the `${expr}` templates
on Webhook and Log. Expressions are JEXL-family, evaluated with no reflection and no static
method or class access.

## Modules

| Module | Contents | Depends on |
|---|---|---|
| `expression-api` | `ExpressionPort`, `ExpressionContext`, `ExpressionException` | `kernel-values` |
| `expression-testkit` | `ExpressionContract`, the mechanical contract every port must pass | `expression-api`, JUnit, AssertJ |
| `expression-jexl-adapter` | `JexlExpressionPort`, the default sandboxed adapter | `expression-api`, Commons JEXL 3 |
| `expression-spring-boot-autoconfig` | Wires the JEXL port when `sequeless.expression.adapter=jexl` | `expression-jexl-adapter`, Spring Boot |

The root POM is an aggregator and is never released. Each module is released on its own.

## Build

```bash
source ~/.bashrc.ext   # exports the GitHub Packages read token
make verify            # ./mvnw -B verify
```

The parent POM and the kernel come from GitHub Packages, so a `read:packages` token is needed.
Run `make fmt` before `make verify` if Spotless reports a layout difference.
