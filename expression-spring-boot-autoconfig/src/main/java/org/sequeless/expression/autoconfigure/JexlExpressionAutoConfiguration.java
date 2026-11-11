package org.sequeless.expression.autoconfigure;

import org.sequeless.expression.api.ExpressionPort;
import org.sequeless.expression.jexl.JexlExpressionPort;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

/**
 * Wires {@link JexlExpressionPort} into the Spring context when {@code
 * sequeless.expression.adapter} is set to {@code jexl}. The class declares no {@code
 * matchIfMissing} and no conditional on a missing bean, so an application that also defines an
 * {@link ExpressionPort} bean gets either a startup failure or two beans. See the how-to <a
 * href="https://github.com/sequeless/sequeless-expression/blob/main/docs/how-to/enable-the-jexl-adapter.md">enabling
 * the JEXL adapter</a>.
 */
@AutoConfiguration
@ConditionalOnProperty(name = "sequeless.expression.adapter", havingValue = "jexl")
public class JexlExpressionAutoConfiguration {

    /** Registers the JEXL-backed {@link ExpressionPort} bean. */
    @Bean
    public ExpressionPort expressionPort() {
        return new JexlExpressionPort();
    }
}
