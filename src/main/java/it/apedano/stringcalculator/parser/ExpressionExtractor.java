package it.apedano.stringcalculator.parser;

import it.apedano.stringcalculator.model.Expression;
import it.apedano.stringcalculator.model.StateSingleton;

/**
 * Created by Alex on 27/11/2017.
 */
public class ExpressionExtractor {

    /**
     * Extract the root expression from the current state
     * @return
     */
    public static Expression extract(){
        Expression out = StateSingleton.getInstance().getCurrentExpression();
        while(out != null && out.getParent()!=null){
            out = out.getParent();
        }
        return out;
    }


}
