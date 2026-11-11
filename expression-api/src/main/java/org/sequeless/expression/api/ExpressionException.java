package org.sequeless.expression.api;

/**
 * Thrown by {@link ExpressionPort#evaluate} and {@link ExpressionPort#renderTemplate} when the
 * expression they were asked to evaluate cannot produce a result: a syntax error, a sandbox
 * rejection (an attempt at reflection or a static method/class reference), or a JEXL-{@code null}
 * result.
 *
 * <p>This is deliberately unchecked: a malformed or rejected expression is a condition that
 * intermediate signatures should not have to declare, and an inbound adapter is expected to catch
 * it once, at the edge.
 *
 * <p>This exception carries no structured report. An implementation wraps the underlying failure it
 * caught, typically a JEXL library's exception, as the {@linkplain #getCause() cause}; the
 * two-argument constructor exists for that purpose.
 */
public final class ExpressionException extends RuntimeException {

    /**
     * Creates an exception with a message describing why the expression failed.
     *
     * @param message a message describing why the expression could not produce a result; may be
     *     {@code null}
     */
    public ExpressionException(String message) {
        super(message);
    }

    /**
     * Creates an exception that wraps the failure that made the expression fail.
     *
     * @param message a message describing why the expression could not produce a result; may be
     *     {@code null}
     * @param cause the underlying failure this exception wraps, typically thrown by the JEXL
     *     library an adapter is built on; may be {@code null}
     */
    public ExpressionException(String message, Throwable cause) {
        super(message, cause);
    }
}
