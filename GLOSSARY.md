# Glossary

Domain terms for this repo. Add terms as the domain takes shape (see docs/agents/domain.md).

- **Expression**: JEXL-family text evaluated by an `ExpressionPort`.
- **Port**: the `ExpressionPort` interface that callers evaluate expressions through.
- **Adapter**: an implementation of a port, for example `JexlExpressionPort`.
- **Self**: the map of `ExpressionContext.selfProperties`, keyed by short property name and reached
  as `self.<name>`.
- **Principal**: the `principalId` of an `ExpressionContext`, bound as `principal`.
- **State**: the `selfState` value of an `ExpressionContext`, reached as `self.state`.
- **Template**: text with `${expr}` placeholders, rendered by `renderTemplate`.
