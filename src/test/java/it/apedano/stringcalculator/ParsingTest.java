package it.apedano.stringcalculator;

import it.apedano.stringcalculator.model.Expression;
import it.apedano.stringcalculator.model.StateSingleton;
import it.apedano.stringcalculator.model.operation.ConsecutiveOperationException;
import it.apedano.stringcalculator.observer.ExpressionObserver;
import it.apedano.stringcalculator.observer.OperandObserver;
import it.apedano.stringcalculator.observer.OperationObserver;
import it.apedano.stringcalculator.observer.SqrtObserver;
import it.apedano.stringcalculator.parser.ExpressionExtractor;
import it.apedano.stringcalculator.parser.ExpressionParser;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Alex on 26/11/2017.
 */
public class ParsingTest {

    private ExpressionParser expressionParser = new ExpressionParser();

    @Before
    public void initTest(){
        StateSingleton.resetState();
    }

    @Test
    public void simpleParsingTest() throws Exception, ConsecutiveOperationException {
        String input = "12+[34-(255-5+75/5)]+{71-[1-8*3*2+(15+23)]}";
        Double expectedResult = Double.valueOf(-139.0);
        executeTest(input, expectedResult);
    }

    @Test
    public void simpleParsingTest2() throws Exception, ConsecutiveOperationException {
        String input = "{31-20/5*3+[123-2*(5-4)-10]+[12*(2+1)]}-12";
        Double expectedValue = Double.valueOf(154.0);
        executeTest(input, expectedValue);
    }

    @Test
    public void doubleOperationExpressionTest() throws Exception, ConsecutiveOperationException {
        String input = "-3+{31-20/5*3+[123-+2*(5-4)-10]+[12*(-2+-1)]}-12";
        Double expectedValue = Double.valueOf(79.0);
        executeTest(input, expectedValue);
    }

    @Test
    public void simpleExpressionParsingTest() throws Exception, ConsecutiveOperationException {
        String input = "3-2/2+5-7+10*2";
        Double expectedValue = Double.valueOf(20.0);
        executeTest(input, expectedValue);
    }

    @Test
    public void simpleUnaryOperationExpressionParsingTest() throws Exception, ConsecutiveOperationException {
        String input = "3-2/2+sqrt144+5-7+10*2";
        Double expecetedValue = Double.valueOf(32.0);
        executeTest(input, expecetedValue);
    }

    private void executeTest(String inputExpressionString, Double expectedValue) throws Exception, ConsecutiveOperationException {
        emitObservableEvents(prepareObservableExpression(inputExpressionString));
        expressionParser.parse();
        Expression rootExpression = ExpressionExtractor.extract();
        System.out.println("Valore calcolato dell'espressione: " + rootExpression.getResult());
        assertEquals(Double.valueOf(rootExpression.getResult()), expectedValue);
        System.out.println("Esecuzione terminata!");
    }

    private ObservableExpression prepareObservableExpression(String expressionString) {
        ObservableExpression observableExpression = new ObservableExpression(expressionString);
        observableExpression.subscribe(new OperandObserver());
        observableExpression.subscribe(new OperationObserver());
        observableExpression.subscribe(new ExpressionObserver());
        observableExpression.subscribe(new SqrtObserver());
        return observableExpression;
    }

    private void emitObservableEvents(ObservableExpression observableExpression) throws Exception {
        char currentEmittedChar = '0';
        while (currentEmittedChar != ObservableExpression.TERMINATION_CHARACTER) {
            observableExpression.emit();
            currentEmittedChar = observableExpression.getEmittedChar();
        }
        System.out.println("Sequenza osservabile terminata!");
    }
}
