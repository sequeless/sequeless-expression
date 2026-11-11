# Design Preferences

## Philosophy

Design decisions in this project should prioritise:

1. **Minimal and modular.** Implement only what's needed. Avoid over-engineering or speculative design.
2. **Maintainable.** Favour clarity, loose coupling and high cohesion.
3. **Open to evolve.** Design to support future alternatives without major rewrites. Avoid locking into a single implementation.

## Architectural pattern: hexagonal (ports and adapters)

Each module has a single root package (for example `org.sequeless.expression.api`). Each module's
`BoundaryRulesTest` (ArchUnit, main code only) limits which packages that module may depend on:

- **`expression-api`**: no Spring. Only the JDK, its own package and `kernel-values`.
- **`expression-testkit`**: no Spring, no JEXL and no adapter package.
- **`expression-jexl-adapter`**: no Spring. Only the JDK, Commons JEXL, `expression-api`,
  `kernel-values` and itself.
- **`expression-spring-boot-autoconfig`**: only the JDK, Spring, the expression packages and kernel
  types. It is the Spring module, so it has no no-Spring rule.

The Spring, Jena and Lombok ban on core modules comes from the parent POM's enforcer, active where
`sequeless.enforcer.core.skip=false` (api, testkit and the JEXL adapter). The autoconfig module
inherits the parent default of `true`.

## Adapter strategy

Each port has a default adapter, and a configuration property selects it, not code branching
or feature flags. Contract tests in a testkit module hold every adapter to the same guarantees.

## How agents should apply this

When grilling or recommending architecture:

1. **Ask about boundaries.** Does this logic belong in the core, or behind a new port in an adapter?
2. **Prefer ports to magic.** Propose a new port before baking a cross-cutting concern into core.
3. **Sketch adapters.** Suggest two or three plausible adapter implementations before settling a port.
4. **Avoid premature unification.** Don't merge similar concerns into one port until they share semantics.
5. **Configuration over code.** Recommend configurable adapter selection, not compile-time choices or constants.

## Reference

- **Decision records**: `docs/adr/`. Consult them before revisiting a design choice.
- **Glossary**: `GLOSSARY.md`, read before ADRs.
