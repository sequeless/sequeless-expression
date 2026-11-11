# Implement an expression port

`expression-testkit` provides `ExpressionContract`, an abstract JUnit class that runs the
`ExpressionPort` contract tests against an implementation. To run it against your own port:

1. Add `expression-testkit` (latest release) to your test scope. It brings JUnit 6 Jupiter API
   classes. Also add a JUnit 6 engine, such as `junit-jupiter-engine`, so the tests can run.
2. Extend `ExpressionContract` and return your implementation from `port()`:

```java
class MyPortContractTest extends ExpressionContract {

    @Override
    protected ExpressionPort port() {
        return new MyPort();
    }
}
```

3. Name the class with a `Test` suffix. The sequeless parent POM runs only `**/*Test.java`
   (the surefire `<includes>` in `sequeless-build-parent` 0.1.3). Surefire's default patterns
   apply if you do not use that parent.
4. Run the build.

## What the contract covers

The contract's tests cover null-argument handling, the `self`, `self.state` and `principal`
bindings, basic evaluation, template rendering and a few error cases. They do not test every clause
of `ExpressionPort`'s Javadoc. Passing the contract is necessary, not sufficient, for an adapter.
