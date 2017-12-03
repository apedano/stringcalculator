package it.apedano.stringcalculator.model.operation;

import it.apedano.stringcalculator.model.Expression;
import it.apedano.stringcalculator.model.ExpressionElement;
import it.apedano.stringcalculator.model.Operand;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Alex on 26/11/2017.
 * Operation class abstracts the generic mathematical operation.
 */
public abstract class Operation extends ExpressionElement {

    /**
     * Verify is the input ElementList contains a valid operand at the given index
     * @param expressionElementList
     * @param operandIndex
     * @return
     * @throws Exception
     * @throws ConsecutiveOperationException
     */
    protected Operand verifyOperand(List<ExpressionElement> expressionElementList, int operandIndex) throws Exception, ConsecutiveOperationException {
        ExpressionElement expressionElement = null;
        try{
            expressionElement = expressionElementList.get(operandIndex);
        } catch(IndexOutOfBoundsException iobe){
            throw new Exception("Impossible to extract operand at index ["+ operandIndex + "] of expression list: "+expressionElementList.toString());
        }
        if(expressionElement == null){
            throw new Exception("Null operand not allowed");
        }
        if(expressionElement instanceof Operation){
            throw new ConsecutiveOperationException();
        }
        if(expressionElement instanceof Expression){
            if(((Expression) expressionElement).getResult() == null){
                throw new Exception("Impossible to extract value from unresolved expression: " + expressionElement.toString());
            }
            return new Operand(((Expression) expressionElement).getResult());
        }
        if(!(expressionElement instanceof Operand)){
            throw new Exception("ExpressionElement [" + expressionElement.toString() +"] is not an operand of " + this.getClass().getSimpleName() + " operation");
        }
        if(((Operand) expressionElement).getValue() == null){
            throw new Exception("Null operand not allowed ");
        }
        return (Operand) expressionElement;
    }

    /**
     * Priority of operation execution inside a generic expression
     * @return
     */
    public abstract Integer getPriority();

    /**
     * This method allow operation resolution over a list of expression elements, where the operations is at the given index
     * This method sobstitute the operation element along with his operand with another operand with the result value
     * @param expressionElementList - the list of expressionElement containing the operation at the given index
     * @param operationIndex - the index integer, inside the list, where Operation is present
     * @throws Exception
     * @throws ConsecutiveOperationException
     */
    public abstract void execute(List<ExpressionElement> expressionElementList, int operationIndex) throws Exception, ConsecutiveOperationException;

    @Override
    public String toString() {
        return "Operation{ " + this.getClass().getSimpleName() + "}";
    }
}
