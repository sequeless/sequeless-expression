/**
 * The {@code ExpressionPort} vocabulary: evaluating JEXL-family expressions authored in the
 * ontology — {@code sq:guard}, {@code sq:value}/{@code sq:expression} on {@code sq:SetProperty}
 * and {@code sq:CreateObject}, and the {@code ${expr}} templates on {@code sq:Webhook} and {@code
 * sq:Log} — against the state of a single object, without the core (or any adapter that only
 * needs to *call* the port) depending on a JEXL library directly.
 *
 * <p>This package imports nothing outside the JDK ({@code java.*}) except {@link
 * org.sequeless.kernel.values.Value} from the kernel-values artifact, which is the only cross-package
 * reference this package makes. {@link org.sequeless.expression.api.ExpressionPort} is the
 * outbound port itself, split into two methods rather than one because the ontology carries two
 * distinct kinds of expression source: {@link org.sequeless.expression.api.ExpressionPort#evaluate}
 * takes a plain JEXL expression (no {@code ${}} wrapper) and returns a single {@link
 * org.sequeless.kernel.values.Value} — this is what {@code sq:guard} and {@code sq:SetProperty}/{@code
 * sq:CreateObject}'s {@code sq:value}/{@code sq:expression} use — while {@link
 * org.sequeless.expression.api.ExpressionPort#renderTemplate} takes a JXLT {@code ${expr}} template
 * and returns the fully-substituted string, which is what {@code sq:Webhook}'s {@code sq:url}/
 * {@code sq:body} and {@code sq:Log}'s {@code sq:message} use. Collapsing these into one method
 * would force every caller to wrap or unwrap {@code ${}} syntax itself; keeping them separate keeps
 * "every expression in Sequeless goes through the {@code ExpressionPort}" literally true without
 * inventing a bespoke templating layer on top of it. {@link
 * org.sequeless.expression.api.ExpressionContext} is the request-scoped state both methods
 * evaluate against — the object's own properties (keyed by short name, so a script can write {@code
 * self.owner}), its current state, and the acting principal's id. {@link
 * org.sequeless.expression.api.ExpressionException} is the unchecked exception both methods throw
 * on a syntax error, a sandbox rejection, or a JEXL-null result.
 */
package org.sequeless.expression.api;
