package it.apedano.stringcalculator.model.operation;

import it.apedano.stringcalculator.model.ExpressionElement;
import it.apedano.stringcalculator.model.Operand;

import java.util.List;

/**
 * Created by Alex on 26/11/2017.
 * BinaryOperation class is a generic class for all mathematical operation that need two operands to be executed
 */
public abstract class BinaryOperation extends Operation {

    /**
     * This method implements the actual mathematical operation executed by the operation
     * @param beforeOperand
     * @param afterOperand
     * @return
     */
    protected abstract Operand applyInternalOperation(Operand beforeOperand, Operand afterOperand);

    @Override
    public void execute(List<ExpressionElement> expressionElementList, int operationIndex) throws Exception, ConsecutiveOperationException {
        Operand beforeOperand = verifyOperand(expressionElementList, operationIndex - 1);
        Operand afterOperand = verifyOperand(expressionElementList, operationIndex + 1);
        Operand resultOperand = applyInternalOperation(beforeOperand, afterOperand);
        expressionElementList.remove(operationIndex - 1);
        expressionElementList.add(operationIndex - 1, resultOperand);
        expressionElementList.remove(operationIndex);
        expressionElementList.remove(operationIndex);
    }


}
