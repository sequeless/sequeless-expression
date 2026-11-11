# expression-api

The `ExpressionPort` interface and the types around it: `ExpressionContext`, the bindings an
expression sees, and `ExpressionException`, which evaluation throws when an expression cannot
produce a result.

- Coordinates: `org.sequeless:expression-api`, latest release.
- Depends on: `kernel-values`, for the `Value` results.

```java
ExpressionContext context = new ExpressionContext(
        Map.of("title", Value.text("Launch")), Optional.of("Active"), "user-1");
Value result = port.evaluate("self.title == 'Launch'", context);
```

`evaluate` returns a `Value`. `renderTemplate` renders `${expr}` placeholders in a template to text.

See the [repository README](../README.md) for the module set and the quick start.
