package it.apedano.stringcalculator.parser;

import it.apedano.stringcalculator.model.Expression;
import it.apedano.stringcalculator.model.ExpressionElement;
import it.apedano.stringcalculator.model.Operand;
import it.apedano.stringcalculator.model.operation.ConsecutiveOperationException;
import it.apedano.stringcalculator.model.operation.Operation;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alex on 26/11/2017.
 */
public class ExpressionParser {

    public void parse() throws Exception, ConsecutiveOperationException {
        System.out.println("Avvio estrazione espressione radice...");
        Expression root = ExpressionExtractor.extract();
        System.out.println("Avvio risoluzione espressione radice...");
        resolveExpressions(root);
        System.out.println("Risoluzione espressione radice terminata!");
    }

    /**
     * This method applies the resolution algorithm to an Expression instance.
     * If Expression contains unresolved nested Expression, the algorithm will be called recursively
     * on the nested expression
     * @param expression - the input expression
     * @throws Exception
     * @throws ConsecutiveOperationException
     */
    private void resolveExpressions(Expression expression) throws Exception, ConsecutiveOperationException {
        List<ExpressionElement> expressionElementList = expression.getExpressionElementList();
        for (int i = 0; i < expressionElementList.size(); i++) {
            //check nested unresolved expressions
            if (expressionElementList.get(i) instanceof Expression && ((Expression) expressionElementList.get(i)).getResult() == null) {
                resolveExpressions((Expression) expressionElementList.get(i));
            }
        }
        Double value = calculateResult(expression);
        System.out.println("Expression value calculated (" + value + "): " + expression);
    }

    /**
     * This method resolves the input expression when it doesn't contains unresolved nested expressions.
     * Creates a copy of the input Expression element list reducing it every time an internal operation
     * (in operation's priority order) is executes.
     * @param expression
     * @return the double value stored in the unique Operand resulting by the sequence of operation result substitution
     * @throws Exception
     * @throws ConsecutiveOperationException
     */
    private Double calculateResult(Expression expression) throws Exception, ConsecutiveOperationException {
        List<ExpressionElement> expressionElementList = (List<ExpressionElement>) ((LinkedList) expression.getExpressionElementList()).clone();
        List<Operation> operationByPriority;
        while (!(operationByPriority = extractOperationByPriority(expressionElementList)).isEmpty()) {
            for (Operation op : operationByPriority) {
                int operationIndex = expressionElementList.indexOf(op);
                op.execute(expressionElementList, operationIndex);
            }
        }
        //there are no more operations in the expression list
        //finally, the list will contain only one operand with the final value
        Double expressionFinalValue = ((Operand) expressionElementList.get(0)).getValue();
        expression.setResult(expressionFinalValue);
        return expressionFinalValue;
    }

    /**
     * Creates a list of operation contained in the input list, sorted by inner operation priority
     * @param expressionElementList
     * @return
     */
    private List<Operation> extractOperationByPriority(List<ExpressionElement> expressionElementList) {
        return expressionElementList
                .stream()
                .filter(exprEl -> exprEl instanceof Operation)
                .map(exprEl -> (Operation) exprEl)
                .sorted(new Comparator<ExpressionElement>() {
                    @Override
                    public int compare(ExpressionElement o1, ExpressionElement o2) {
                        return Double.valueOf(((Operation) o2).getPriority()).compareTo(
                                Double.valueOf(((Operation) o1).getPriority()));
                    }
                }).collect(Collectors.toList());
    }


}
