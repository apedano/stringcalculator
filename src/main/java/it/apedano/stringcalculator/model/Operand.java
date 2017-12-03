package it.apedano.stringcalculator.model;

/**
 * Created by Alex on 26/11/2017.
 * Implements a generic operand as implementation of ExpressionElement class
 * It stores the inner numeric value.
 */

public class Operand extends ExpressionElement {
    Double value;

    public Operand(Double value){
        setValue(value);
    }

    public Operand() {
        this.value = null;
        this.text = "";
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.text = String.valueOf(value);
        this.value = value;
    }

    @Override
    public String toString() {
        return "Operand{" +
                "value=" + value +
                '}';
    }
}
