package ValidatorTests;

import org.junit.Test;
import utils.Exceptions.WrongExpressionFormat;
import utils.Validator;

import static org.junit.Assert.assertTrue;

public class BracketsTests {

    @Test
    public void ValidData(){
        String expression = "1 + (4-3)";
        boolean passed = true;
        try {
            Validator.validate(expression);
        } catch (WrongExpressionFormat e) {
            passed = false;
        }
        assertTrue(passed);
    }

    @Test
    public void ValidDataWithManyBrackets(){
        String expression = "(1 + (4-3)/6)+(4-(5+12))";
        boolean passed = true;
        try {
            Validator.validate(expression);
        } catch (WrongExpressionFormat e) {
            passed = false;
        }
        assertTrue(passed);
    }

    @Test
    public void UnbalancedBrackets1(){
        String expression = "(1) + (4-3)/6)+(4-(5+12))";

        try {
            Validator.validate(expression);
        } catch (WrongExpressionFormat e) {
            assertTrue(e.getMessage().equals("brackets are unbalanced"));
        }
    }

    @Test
    public void UnbalancedBrackets2(){
        String expression = ")((1 + (4-3)/6)+(4-(5+12))";

        try {
            Validator.validate(expression);
        } catch (WrongExpressionFormat e) {
            assertTrue(e.getMessage().equals("brackets are unbalanced"));
        }

    }

    @Test
    public void ToManyBalancedBrackets(){
        String expression = "((((((((((((((1 + (4-3)/6)+(4-(5+12)))))))))))))))";
        boolean passed = true;
        try {
            Validator.validate(expression);
        } catch (WrongExpressionFormat e) {
            passed = false;
        }
        assertTrue(passed);
    }

    @Test
    public void EmptyExpression(){
        String expression = "";
        boolean passed = true;
        try {
            Validator.validate(expression);
        } catch (WrongExpressionFormat e) {
            passed = false;
        }
        assertTrue(passed);
    }

    @Test
    public void BracketsInCenter(){
        String expression = "1+(2+3)+4";
        boolean passed = true;
        try {
            Validator.validate(expression);
        } catch (WrongExpressionFormat e) {
            passed = false;
        }
        assertTrue(passed);
    }

    @Test
    public void WithoutBrackets(){
        String expression = "1+2+3+4";
        boolean passed = true;
        try {
            Validator.validate(expression);
        } catch (WrongExpressionFormat e) {
            passed = false;
        }
        assertTrue(passed);
    }
}
