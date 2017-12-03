package it.apedano.stringcalculator.observer;

import it.apedano.stringcalculator.ObservableExpression;
import it.apedano.stringcalculator.model.StateSingleton;
import it.apedano.stringcalculator.model.operation.Operation;

/**
 * Created by Alex on 30/11/2017.
 * The CumulativeObserver implements observer with an internal state, because they need
 * multiple emitted character to create the corresponding operation. It maintains last emitted chars
 * checking if the operation name is emitting. When the complete op name is emitted the new Operation
 * is added to the current state
 */
public abstract class CumulativeObserver<E extends Operation> extends Observer {

    protected int nameIndex = 0;

    protected abstract char[] getOperationName();

    protected abstract E getOperation();

    protected void addOperation(){
        StateSingleton.getInstance().getCurrentExpression().addElement(getOperation());
    }

    protected void handleEmitted(ObservableExpression observableExpression) throws Exception{
        char emitted = observableExpression.getEmittedChar();
        if(emitted == getOperationName()[nameIndex]){
            nameIndex++;
            setValidCharacter();
            if(nameIndex == getOperationName().length){
                nameIndex = 0;
                addOperation();
            }
        } else {
            //reset the counter
            nameIndex = 0;
        }
    }

}
