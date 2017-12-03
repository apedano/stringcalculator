package it.apedano.stringcalculator.observer;

import it.apedano.stringcalculator.ObservableExpression;
import it.apedano.stringcalculator.model.ExpressionElement;
import it.apedano.stringcalculator.model.Operand;
import it.apedano.stringcalculator.model.StateSingleton;

/**
 * Created by Alex on 26/11/2017.
 */
public class OperandObserver extends Observer {

    private Operand internalState = null;

    @Override
    public void notify(ObservableExpression observableExpression) {
        char emitted = observableExpression.getEmittedChar();
        //TODO: inserire il parsing del punto o della virgola
        if(Character.isDigit(emitted)){
            if(internalState == null){
                internalState = new Operand();
            }
            internalState.setText(internalState.getText() + emitted);
            setValidCharacter();
        } else {
            if(internalState != null){
                internalState.setValue(Double.parseDouble(internalState.getText()));
                StateSingleton.getInstance().getCurrentExpression().addElement(internalState);
            }
            //reset the internal state
            internalState = null;
        }
    }
}
