# Enable the JEXL adapter

`expression-spring-boot-autoconfig` registers a `JexlExpressionPort` as the `ExpressionPort` bean
when the property `sequeless.expression.adapter` is set to `jexl`:

```properties
sequeless.expression.adapter=jexl
```

The outcomes below hold with Spring Boot 4.1.1 defaults, where bean definition overriding is
disabled. Of these outcomes, `JexlExpressionAutoConfigurationTest` covers only the third:

- A user bean named `expressionPort` plus `adapter=jexl` fails startup with
  `BeanDefinitionOverrideException`.
- A user bean with a different name plus `adapter=jexl` makes
  `getBean(ExpressionPort.class)` throw `NoUniqueBeanDefinitionException`.
- With the property unset, no `ExpressionPort` bean is registered. The context starts, unless a
  bean in your application requires an `ExpressionPort`. `getBean(ExpressionPort.class)` throws
  `NoSuchBeanDefinitionException`.

When you use the adapter, do not define a second `ExpressionPort` bean. Wire one implementation and
let the property select it.

The module is built against Spring Boot 4.1.1. Other Spring Boot versions are not tested.
