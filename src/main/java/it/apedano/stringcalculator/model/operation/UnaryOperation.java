package it.apedano.stringcalculator.model.operation;

import it.apedano.stringcalculator.model.ExpressionElement;
import it.apedano.stringcalculator.model.Operand;

import java.util.List;

/**
 * Created by Alex on 26/11/2017.
 */
public abstract class UnaryOperation extends Operation {

    @Override
    public void execute(List<ExpressionElement> expressionElementList, int operationIndex) throws Exception {
        try {
            Operand afterOperand = verifyOperand(expressionElementList, operationIndex + 1);
            Operand resultOperand = applyInternalOperation(afterOperand);
            expressionElementList.remove(operationIndex);
            expressionElementList.add(operationIndex, resultOperand);
            expressionElementList.remove(operationIndex + 1);
        } catch(ConsecutiveOperationException coe){
            System.out.println("Consecutive operations will be resolved in different loops");
        }
    }

    /**
     * This method implements the actual mathematical operation executed by the operation
     * @param operand
     * @return
     */
    protected abstract Operand applyInternalOperation(Operand operand);

}
