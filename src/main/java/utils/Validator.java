package utils;

import utils.Exceptions.WrongExpressionFormat;

import java.util.List;
import java.util.Stack;

public class Validator {
    public static String validate(String expression) throws WrongExpressionFormat {
        expression = expression.replace(" ", "");

        expression = validateOperators(expression);
        validateBrackets(expression);

        return expression;
    }

    private static void validateBrackets(String expression) throws WrongExpressionFormat {
        String onlyBrackets = expression.replaceAll("[0-9|+|\\-|*|\\/|.]", "");
        Stack<Character> stack = new Stack();
        for (int i = 0; i < onlyBrackets.length(); i++) {
            char x = onlyBrackets.charAt(i);
            if (x == '(') {
                stack.push(x);
                continue;
            }
            if (stack.isEmpty()) throw new WrongExpressionFormat("brackets are unbalanced");
            stack.pop();
        }
        if(!stack.isEmpty()) throw new WrongExpressionFormat("brackets are unbalanced");
    }

    private static String validateOperators(String expression) throws WrongExpressionFormat {
        List<String> operators = List.of("*", "/", "+", "-");
        List<String> forbiddenDoubleOperators = List.of("*", "/", "+");
        for (int i = 0; i < expression.length(); i++) {
            if(operators.contains(String.valueOf(expression.charAt(i)))) {
                if(forbiddenDoubleOperators.contains(String.valueOf(expression.charAt(i+1)))){//if next char after operator in forbidden operators
                    throw new WrongExpressionFormat(expression.charAt(i+1) + " can't be after symbol " + expression.charAt(i));
                }
                if (String.valueOf(expression.charAt(i+1)).equals("-")){
                    expression = addBracketsAroundDoubleOperator(expression, i+1);
                }
            }
        }

        return expression;
    }

    private static String addBracketsAroundDoubleOperator(String expression, int startIndex){
        String updatedExpression = expression.substring(0, startIndex) + "(" + expression.substring(startIndex);
        int numEnd = startIndex+1;
        for (int i = startIndex+2; i < updatedExpression.length(); i++) {
            if(!String.valueOf(updatedExpression.charAt(i)).matches("[1-9|.]")){
                break;
            }
            numEnd = i+1;
        }
        updatedExpression = updatedExpression.substring(0, numEnd) + ")" + updatedExpression.substring(numEnd);
        return updatedExpression;
    }
}
