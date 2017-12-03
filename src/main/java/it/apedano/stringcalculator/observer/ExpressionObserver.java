package it.apedano.stringcalculator.observer;

import it.apedano.stringcalculator.ObservableExpression;
import it.apedano.stringcalculator.model.*;
import it.apedano.stringcalculator.model.operation.Operation;

/**
 * Created by Alex on 26/11/2017.
 */
public class ExpressionObserver extends Observer{

    @Override
    public void notify(ObservableExpression observableExpression) throws Exception{
        char emitted = observableExpression.getEmittedChar();
        switch(emitted){
            case '{':
                openExpression(ExpressionLimit.BRACES);
                break;
            case '}':
                closeExpression(ExpressionLimit.BRACES);
                break;
            case '[':
                openExpression(ExpressionLimit.SQUARE_BRACKET);
                break;
            case ']':
                closeExpression(ExpressionLimit.SQUARE_BRACKET);
                break;
            case '(':
                openExpression(ExpressionLimit.PARENTHESIS);
                break;
            case ')':
                closeExpression(ExpressionLimit.PARENTHESIS);
                break;
        }
    }

    private void openExpression(ExpressionLimit parenthesis) throws ParenthesisWithNoPrecedingOperationException {
        //we need to verify that the previous expression element is an Operand
        Expression newExpression = new Expression(parenthesis, StateSingleton.getInstance().getCurrentExpression());
        StateSingleton.getInstance().getCurrentExpression().addElement(newExpression);
        StateSingleton.getInstance().setCurrentExpression(newExpression);
        setValidCharacter();
    }

    private void closeExpression(ExpressionLimit parenthesis) throws UnbalancedParenthesisException {
        //we need to verify we are closing an expression of the same type of the current one
        if(StateSingleton.getInstance().getCurrentExpression().getExpressionLimit() != parenthesis){
            throw new UnbalancedParenthesisException(parenthesis.toString());
        }
        //we return to the parent expression
        StateSingleton.getInstance().setCurrentExpression((Expression) StateSingleton.getInstance().getCurrentExpression().getParent());
        setValidCharacter();

    }
}
