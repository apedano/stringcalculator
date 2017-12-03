package it.apedano.stringcalculator.model.operation;

import it.apedano.stringcalculator.model.ExpressionElement;
import it.apedano.stringcalculator.model.Operand;

import java.util.List;

/**
 * Created by Alex on 30/11/2017.
 */
public class SqrtOperation extends UnaryOperation {

    public static final String SQRT_OPERATION_NAME = "sqrt";

    @Override
    public Integer getPriority() {
        return 5000;
    }


    @Override
    protected Operand applyInternalOperation(Operand operand) {
        return new Operand(Math.sqrt(operand.getValue()));
    }
}
