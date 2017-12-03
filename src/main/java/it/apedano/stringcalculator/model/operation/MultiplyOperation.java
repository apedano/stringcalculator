package it.apedano.stringcalculator.model.operation;

import it.apedano.stringcalculator.model.ExpressionElement;
import it.apedano.stringcalculator.model.Operand;

import java.util.List;

/**
 * Created by Alex on 29/11/2017.
 */
public class MultiplyOperation extends BinaryOperation {

    public static final Character operationCharacter = '*';

    @Override
    public Integer getPriority() {
        return 3000;
    }


    @Override
    protected Operand applyInternalOperation(Operand beforeOperand, Operand afterOperand) {
        return new Operand(beforeOperand.getValue() * afterOperand.getValue());
    }
}
