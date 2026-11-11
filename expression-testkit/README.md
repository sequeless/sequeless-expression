# expression-testkit

`ExpressionContract`, an abstract JUnit class that runs the `ExpressionPort` contract tests against
an implementation. Subclass it and return your implementation from `port()`. The step-by-step guide is
[implement an expression port](../docs/how-to/implement-an-expression-port.md).

- Coordinates: `org.sequeless:expression-testkit`, latest release.
- Compile-scope dependencies: `expression-api`, `kernel-values`, `junit-jupiter-api` and
  `assertj-core`. Consumers also need a JUnit 6 engine to run the tests.
- The contract does not test every clause of `ExpressionPort`'s Javadoc.
