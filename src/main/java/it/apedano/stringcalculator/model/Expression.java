package it.apedano.stringcalculator.model;

import it.apedano.stringcalculator.model.operation.PlusOperation;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alex on 26/11/2017.
 * Expression class implements a set of ExpressionElements tha produces a numeric result.
 * It can be an atomic or hierarchical with nested expression limited by brackets
 *
 * Internally, it stores a list of ExpressionElements, a parent element (in case of nested expressions with brackets)
 */
public class Expression extends ExpressionElement {

    protected List<ExpressionElement> expressionElementList;
    protected Double result = null;
    private Expression parent;
    protected ExpressionLimit expressionLimit = null;

    public Expression() {
        this.expressionElementList = initExpressionElementList();
    }

    /**
     *
     * @param expressionLimit - in case of nested Expression, expressionLimit is the tye ob enclosing bracket
     * @param parent - the parent expression in parsing process
     */
    public Expression(ExpressionLimit expressionLimit, Expression parent) {
        this.expressionLimit = expressionLimit;
        //this is the parent expression (for nested expressions)
        this.setParent(parent);
        this.expressionElementList = initExpressionElementList();
    }

    /**
     * This method initializes a new ExpresssionElementList for a new Exoression
     * adding at the head of the list a zero operand and a plus operation to avoid
     * parsing errors when expression starts with a plus or minus operation character
     * (it can be an initial relative operand or an invalid expression beginning as well)
     * @return
     */
    private List<ExpressionElement> initExpressionElementList(){
        List<ExpressionElement> expressionElementList = new LinkedList<ExpressionElement>();
        expressionElementList.add(new Operand(0.0));
        expressionElementList.add(new PlusOperation());
        return expressionElementList;
    }

    public void addElement(ExpressionElement element){
        this.expressionElementList.add(element);
    }

    /**
     * If the parsing process resolves the expression the resulting value is stored here
     * @return
     */
    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public ExpressionLimit getExpressionLimit() {
        return expressionLimit;
    }

    public List<ExpressionElement> getExpressionElementList() {
        return expressionElementList;
    }

    public Expression getParent() {
        return parent;
    }

    public void setParent(Expression parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Expression{" +
                "expressionElementList=" + expressionElementList +
                '}';
    }
}
