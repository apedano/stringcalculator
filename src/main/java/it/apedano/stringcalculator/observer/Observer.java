package it.apedano.stringcalculator.observer;

import it.apedano.stringcalculator.ObservableExpression;
import it.apedano.stringcalculator.model.StateSingleton;

/**
 * Observer abstract class implements observer entity in Observable pattern
 * Created by Alex on 26/11/2017.
 */
public abstract class  Observer {

    /**
     * This method allow observableExpression to notify the observer the emit event
     * @param observableExpression - the entity emitting observed events
     * @throws Exception
     */
    public abstract void notify(ObservableExpression observableExpression) throws Exception;

    /**
     * Set current emitted character as valid if this observer is able to handle it.
     */
    protected void setValidCharacter(){
        StateSingleton.getInstance().setCurrentCharIsValid(true);
    }
}
