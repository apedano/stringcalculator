package it.apedano.stringcalculator.model.operation;

import it.apedano.stringcalculator.model.Operand;

/**
 * Created by Alex on 29/11/2017.
 */
public class DivideOperation extends BinaryOperation {

    public static final Character operationCharacter = '/';

    @Override
    protected Operand applyInternalOperation(Operand beforeOperand, Operand afterOperand) {
        return new Operand(beforeOperand.getValue() / afterOperand.getValue());
    }

    @Override
    public Integer getPriority() {
        return 4000;
    }
}
