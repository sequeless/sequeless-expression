package org.sequeless.expression.api;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.sequeless.kernel.values.Value;

/**
 * The request-scoped state an {@link ExpressionPort} call is evaluated against: a single object's
 * own properties, its current state (if its type has a state machine), and the id of the
 * principal on whose behalf the expression is being evaluated — exactly what a {@code sq:guard},
 * a {@code sq:SetProperty}/{@code sq:CreateObject} value expression, or a {@code sq:Webhook}/{@code
 * sq:Log} template needs to reference as {@code self}, {@code self.state}, and {@code principal}.
 *
 * <p>{@link #selfProperties()} is keyed by the property's short (local) name — {@code "owner"},
 * {@code "title"} — not its IRI. That is what lets a JEXL script write {@code self.owner}: JEXL
 * resolves a bean-style {@code .property} or {@code [key]} access on any {@code self} bound to a
 * {@link java.util.Map}, and it has no notion of an IRI-qualified property name. Shortening a
 * property's IRI to this local name is the caller's job, not this record's. Callers build a fresh
 * record, by short name, right before each evaluation; it is not a durable snapshot.
 *
 * @param selfProperties the evaluated object's own property values, keyed by property short name
 *     (not IRI); must not be {@code null}; no value in the map may be {@code null}; returned as an
 *     unmodifiable copy so callers cannot mutate this context after construction
 * @param selfState the evaluated object's current state ({@code self.state}), present only if its
 *     type has a state machine; must not be {@code null} (the {@link Optional} wrapper itself, not
 *     just its contents)
 * @param principalId the id of the principal the expression is evaluated on behalf of ({@code
 *     principal}); must not be {@code null}
 */
public record ExpressionContext(Map<String, Value> selfProperties, Optional<String> selfState, String principalId) {

    public ExpressionContext {
        Objects.requireNonNull(selfProperties, "selfProperties must not be null");
        selfProperties = Map.copyOf(selfProperties);
        Objects.requireNonNull(selfState, "selfState must not be null");
        Objects.requireNonNull(principalId, "principalId must not be null");
    }
}
