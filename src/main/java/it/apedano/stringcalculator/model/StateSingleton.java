package it.apedano.stringcalculator.model;

/**
 * Created by Alex on 26/11/2017.
 * This class implements the Singleton design pattern to store a shared parsing state in expression analysis process.
 * StateSingletons includes a State class definition with the current expression during parsing steps and the boolean information
 * if the current emitted character is valid or not. The current ExpressionElement in the parsing process will be the last element
 * in the inner element list.
 *
 */
public class StateSingleton {
    private static State state = null;

    //private constructor no to be invoked
    private StateSingleton() {
    }

    public static State getInstance() {
        //TODO: rendere thread-safe
        if (state == null) {
            state = new State();
        }
        return state;
    }

    public static void resetState() {
        state = null;
    }

    public static class State {

        private Expression currentExpression = new Expression();
        private boolean currentCharIsValid;

        public Expression getCurrentExpression() {
            return currentExpression;
        }

        public void setCurrentExpression(Expression currentExpression) {
            this.currentExpression = currentExpression;
        }

        public ExpressionElement getCurrentElement() {
            int lastIndex = currentExpression.getExpressionElementList().size() - 1;
            return currentExpression.getExpressionElementList().get(lastIndex);
        }

        public boolean isCurrentCharIsValid() {
            return currentCharIsValid;
        }

        public void setCurrentCharIsValid(boolean currentCharIsValid) {
            this.currentCharIsValid = currentCharIsValid;
        }
    }

}
