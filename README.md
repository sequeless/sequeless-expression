# sequeless-expression

Sandboxed expression evaluation behind the `ExpressionPort` SPI: the guards on state machine
transitions, `sq:value`/`sq:expression` on SetProperty and CreateObject, and the `${expr}` templates
on Webhook and Log. Expressions are JEXL-family, evaluated with no reflection and no static
method or class access.

## Modules

| Module | Contents | Depends on |
|---|---|---|
| [`expression-api`](expression-api/README.md) | `ExpressionPort`, `ExpressionContext`, `ExpressionException` | `kernel-values` |
| [`expression-testkit`](expression-testkit/README.md) | `ExpressionContract`, the mechanical contract every port must pass | `expression-api`, JUnit, AssertJ |
| `expression-jexl-adapter` | `JexlExpressionPort`, the default sandboxed adapter | `expression-api`, Commons JEXL 3 |
| [`expression-spring-boot-autoconfig`](expression-spring-boot-autoconfig/README.md) | Wires the JEXL port when `sequeless.expression.adapter=jexl` | `expression-jexl-adapter`, Spring Boot |

The root POM is an aggregator and is never released. Each module is released on its own.

## Build

Maven reads the GitHub Packages registry, so you need a token with `read:packages` and a `github`
server entry in `~/.m2/settings.xml`:

```xml
<server>
  <id>github</id>
  <username>YOUR_GITHUB_USERNAME</username>
  <password>${env.PACKAGES_READ_TOKEN}</password>
</server>
```

```bash
export PACKAGES_READ_TOKEN=<PAT with read:packages>
make verify    # ./mvnw -B verify
```

The build needs JDK 25. See [CONTRIBUTING.md](CONTRIBUTING.md). Run `make fmt` before `make verify`
if Spotless reports a layout difference.

## Quick start

Consumers need Java 21 at runtime, and the GitHub Packages repository and token above. The
autoconfiguration module is built against Spring Boot 4.1.1. Other Spring Boot versions are not
tested. `kernel-values` also comes from GitHub Packages.

1. Declare the repository in your POM, the same one this repo declares:

```xml
<repositories>
  <repository>
    <id>github</id>
    <url>https://maven.pkg.github.com/sequeless/*</url>
  </repository>
</repositories>
```

2. Depend on the autoconfiguration module, with `<version>` set to the latest release:

```xml
<dependency>
  <groupId>org.sequeless</groupId>
  <artifactId>expression-spring-boot-autoconfig</artifactId>
  <version><!-- latest release --></version>
</dependency>
```

3. Select the JEXL adapter with `sequeless.expression.adapter=jexl`.

4. Inject the port and evaluate a fixed expression against bindings your application builds:

```java
import java.util.Map;
import java.util.Optional;
import org.sequeless.expression.api.ExpressionContext;
import org.sequeless.expression.api.ExpressionPort;
import org.sequeless.kernel.values.Value;
import org.springframework.stereotype.Component;

@Component
public class OwnerCheck {

    private final ExpressionPort expressions;

    public OwnerCheck(ExpressionPort expressions) {
        this.expressions = expressions;
    }

    public Value isOwner(String ownerId, String userId) {
        ExpressionContext context = new ExpressionContext(
                Map.of("owner", Value.text(ownerId)), Optional.empty(), userId);
        return expressions.evaluate("self.owner == principal", context);
    }
}
```

For the details, see [enable the JEXL adapter](docs/how-to/enable-the-jexl-adapter.md).

## Documentation

- [Implement an expression port](docs/how-to/implement-an-expression-port.md)
- [Enable the JEXL adapter](docs/how-to/enable-the-jexl-adapter.md)
- Module READMEs: [expression-api](expression-api/README.md),
  [expression-testkit](expression-testkit/README.md),
  [expression-spring-boot-autoconfig](expression-spring-boot-autoconfig/README.md)

Architecture decisions are in [docs/adr/](docs/adr/). Domain terms are in [GLOSSARY.md](GLOSSARY.md).

## License

Apache License 2.0. See [LICENSE](LICENSE).
