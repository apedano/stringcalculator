package it.apedano.stringcalculator;

import it.apedano.stringcalculator.model.StateSingleton;
import it.apedano.stringcalculator.observer.Observer;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by Alex on 26/11/2017.
 * Implemementation of Observable entity in observable pattern
 * The observable state must be immutable, hence the input string (immutable class) is used to create it
 *
 */
public class ObservableExpression {
    public static final char TERMINATION_CHARACTER = 'T';

    private List<Observer> observerList = new LinkedList<Observer>();
    private String expressionString;
    private char emitted;
    private int expressionStringIndex = -1;

    /**
     * Creates a new instance of Observable expression using the input string
     *
     * @param expressionString - the sequence of characters to emit. The input String has white spaces removed and converted in lower case
     */
    public ObservableExpression(String expressionString) {
        //we remove all blank spaces from string and convert it to lower case
        this.expressionString = expressionString.replaceAll("\\s", "").toLowerCase();
    }

    public void subscribe(@Nonnull Observer observer) {
        this.observerList.add(observer);
    }

    public void unsubscribe(@Nonnull Observer observer) {
        this.observerList.remove(observer);
    }

    /**
     * Every event emits a character in sequence of the input string and notify it to all subscribed observers.
     *
     * @throws Exception - If no observer parses the emitted character (invalid char) a new Exception is thrown
     */
    public void emit() throws Exception {
        StateSingleton.getInstance().setCurrentCharIsValid(false);
        expressionStringIndex++;
        if (expressionStringIndex < expressionString.length()) {
            emitted = this.expressionString.charAt(expressionStringIndex);
        } else {
            emitted = TERMINATION_CHARACTER;
        }
        for (Observer observer : observerList) {
            observer.notify(this);
        }
        if(!StateSingleton.getInstance().isCurrentCharIsValid() && emitted != TERMINATION_CHARACTER){
            throw new Exception("Found invalid character ["+ expressionString.charAt(expressionStringIndex) +"] at index[" + expressionStringIndex + "] for expression [" + expressionString + "]");
        }
    }

    public char getEmittedChar() {
        return emitted;
    }
}
