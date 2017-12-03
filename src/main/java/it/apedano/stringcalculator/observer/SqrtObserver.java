package it.apedano.stringcalculator.observer;

import it.apedano.stringcalculator.ObservableExpression;
import it.apedano.stringcalculator.model.operation.SqrtOperation;

/**
 * Created by Alex on 30/11/2017.
 */
public class SqrtObserver extends CumulativeObserver<SqrtOperation> {

    @Override
    protected char[] getOperationName() {
        return SqrtOperation.SQRT_OPERATION_NAME.toCharArray();
    }

    @Override
    protected SqrtOperation getOperation() {
        return new SqrtOperation();
    }

    @Override
    public void notify(ObservableExpression observableExpression) throws Exception {
        handleEmitted(observableExpression);
    }
}
