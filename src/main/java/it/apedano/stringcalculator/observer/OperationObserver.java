package it.apedano.stringcalculator.observer;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import it.apedano.stringcalculator.ObservableExpression;
import it.apedano.stringcalculator.model.operation.*;
import it.apedano.stringcalculator.model.StateSingleton;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alex on 26/11/2017.
 */
public class OperationObserver extends Observer {

    private static Map<Character, Operation> operationObserverMap = new HashMap<Character, Operation>();
    private static Table<Class<? extends Operation>, Class<? extends Operation>, Operation> consecutiveOperationTable = HashBasedTable.create();

    static {
        operationObserverMap.put(MinusOperation.operationCharacter, new MinusOperation());
        operationObserverMap.put(PlusOperation.operationCharacter, new PlusOperation());
        operationObserverMap.put(MultiplyOperation.operationCharacter, new MultiplyOperation());
        operationObserverMap.put(DivideOperation.operationCharacter, new DivideOperation());
        consecutiveOperationTable.put(MinusOperation.class, PlusOperation.class, new MinusOperation());
        consecutiveOperationTable.put(PlusOperation.class, MinusOperation.class, new MinusOperation());
        consecutiveOperationTable.put(PlusOperation.class, PlusOperation.class, new PlusOperation());
        consecutiveOperationTable.put(MinusOperation.class, MinusOperation.class, new PlusOperation());

    }

    @Override
    public void notify(ObservableExpression observableExpression) {
        Character emitted = Character.valueOf(observableExpression.getEmittedChar());
        Operation currentOperation = operationObserverMap.get(emitted);
        Operation previousOperation =
                StateSingleton.getInstance().getCurrentElement() instanceof MinusOperation ||
                        StateSingleton.getInstance().getCurrentElement() instanceof PlusOperation ?
                        (Operation) StateSingleton.getInstance().getCurrentElement() : null;

        if (currentOperation != null && previousOperation != null && consecutiveOperationTable.get(currentOperation.getClass(), previousOperation.getClass()) != null) {
            //there are two consecutive operations
            //we must remove last Operation in the currentList
            int lasteElementIndex = StateSingleton.getInstance().getCurrentExpression().getExpressionElementList().size() - 1;
            StateSingleton.getInstance().getCurrentExpression().getExpressionElementList().remove(lasteElementIndex);
            currentOperation = consecutiveOperationTable.get(currentOperation.getClass(), previousOperation.getClass());
        }
        if (currentOperation != null) {
            StateSingleton.getInstance().getCurrentExpression().addElement(currentOperation);
            setValidCharacter();
        }
    }

}
