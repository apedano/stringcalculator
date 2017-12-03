package it.apedano.stringcalculator.model;

/**
 * Created by Alex on 26/11/2017.
 * It represents the base class for all the elements we can find inside a math expression
 */
public abstract class ExpressionElement {

    protected String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "ExpressionElement{" +
                ", text='" + getText() + '\'' +
                '}';
    }
}
