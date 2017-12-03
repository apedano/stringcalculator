package it.apedano.stringcalculator.model.operation;

import it.apedano.stringcalculator.model.ExpressionElement;
import it.apedano.stringcalculator.model.Operand;

import java.util.List;

/**
 * Created by Alex on 26/11/2017.
 */
public class MinusOperation extends BinaryOperation {

    public static final Character operationCharacter = '-';

    @Override
    public Integer getPriority() {
        return 2000;
    }

    @Override
    protected Operand applyInternalOperation(Operand beforeOperand, Operand afterOperand) {
        return new Operand(beforeOperand.getValue() - afterOperand.getValue());
    }
}
