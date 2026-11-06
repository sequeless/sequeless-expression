package org.sequeless.expression.jexl;

import org.sequeless.expression.api.ExpressionPort;
import org.sequeless.expression.testkit.ExpressionContract;

class JexlExpressionPortContractTest extends ExpressionContract {

    @Override
    protected ExpressionPort port() {
        return new JexlExpressionPort();
    }
}
