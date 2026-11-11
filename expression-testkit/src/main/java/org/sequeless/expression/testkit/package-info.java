/**
 * The mechanical contract test for {@link org.sequeless.expression.api.ExpressionPort}.
 *
 * <p>{@link org.sequeless.expression.testkit.ExpressionContract} asserts part of the
 * behavioural contract documented on {@code ExpressionPort} itself, against any implementation
 * supplied by a subclass via {@code port()}. It checks null-safety, the {@code self}/{@code
 * self.state}/{@code principal} variable bindings, that {@code renderTemplate} correctly
 * interpolates {@code ${expr}} placeholders, and that sandboxing rejects reflection and static
 * method/class access — but it deliberately does not pin down which concrete {@link
 * org.sequeless.kernel.values.Value} subtype numeric literals produce beyond what {@link
 * org.sequeless.kernel.values.Value}'s own static factories guarantee, so this contract stays
 * adapter-agnostic rather than describing one JEXL adapter's own result-conversion choices. The
 * JEXL adapter's {@code JexlExpressionPortContractTest} extends this class and is the standing
 * proof that a real implementation can pass it unmodified.
 */
package org.sequeless.expression.testkit;
