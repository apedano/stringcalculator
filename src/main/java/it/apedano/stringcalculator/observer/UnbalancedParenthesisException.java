package it.apedano.stringcalculator.observer;

/**
 * Created by Alex on 26/11/2017.
 */
public class UnbalancedParenthesisException extends Exception {
    public UnbalancedParenthesisException(String s) {
        super("Unbalanced parenthesis at: " + s);
    }
}
