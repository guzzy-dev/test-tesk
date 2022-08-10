package ValidatorTests;

import org.junit.Test;
import utils.Exceptions.WrongExpressionFormat;
import utils.Validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OperatorsTests {
    @Test
    public void ValidDataWithNoDoubles(){
        String expression = "1 + 4 - 3";
        boolean passed = true;
        try {
            Validator.validate(expression);
        } catch (WrongExpressionFormat e) {
            passed = false;
        }
        assertTrue(passed);
    }

    @Test
    public void ValidDataWithDoubles(){
        String expression = "1 + 4 + - 3";
        boolean passed = true;
        try {
            Validator.validate(expression);
        } catch (WrongExpressionFormat e) {
            passed = false;
        }
        assertTrue(passed);
    }

    @Test
    public void ValidDataWithTwoDoubles(){
        String expression = "1 + - 4 + - 3";
        boolean passed = true;
        try {
            Validator.validate(expression);
        } catch (WrongExpressionFormat e) {
            passed = false;
        }
        assertTrue(passed);
    }


    @Test
    public void InvalidDataWithOneCorrectDouble(){
        String expression = "1 + - 4 + + 3";
        try {
            Validator.validate(expression);
        } catch (WrongExpressionFormat e) {
            assertEquals("+ can't be after symbol +", e.getMessage());
        }

    }


    @Test
    public void FullIncorrectData(){
        String expression = "1 + * 4 + * 3";

        try {
            Validator.validate(expression);
        } catch (WrongExpressionFormat e) {
            assertEquals("* can't be after symbol +", e.getMessage());
        }

    }


}
