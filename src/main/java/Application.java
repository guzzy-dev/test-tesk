import DAO.Impl.ExpressionDAOImpl;
import Model.Expression;
import Service.ExpressionService;
import utils.Exceptions.WrongExpressionFormat;
import utils.Validator;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.util.List;
import java.util.Scanner;

public class Application {
    static ExpressionService expressionService = new ExpressionService(new ExpressionDAOImpl());
    static ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while(true){
            System.out.print("""
                    1 -- create an expression
                    2 -- find an expression by result
                    3 -- find an expression with lower result
                    4 -- find an expression with higher result
                    Enter your choice:\s""");
            String choice = sc.next();

            switch (choice) {
                case "1" -> createExpression();
                case "2" -> findByResult();
                case "3" -> findByHigherResult();
                case "4" -> findByLowerResult();
            }
        }
    }

    private static void findByLowerResult() {
        System.out.print("Enter value of expression to find expressions with higher result: ");
        double resultToFind = Double.parseDouble(sc.next());
        printSearchResult(expressionService.getWithLowerResults(resultToFind));
    }

    private static void findByHigherResult() {
        System.out.print("Enter value of expression to find expressions with lower result: ");
        double resultToFind = Double.parseDouble(sc.next());
        printSearchResult(expressionService.getByHigherResult(resultToFind));
    }

    private static void findByResult() {
        System.out.print("Enter value of expression result: ");
        double resultToFind = Double.parseDouble(sc.next());
        printSearchResult(expressionService.getByResult(resultToFind));
    }

    public static void createExpression(){
        System.out.println("Enter new expression: ");
        Expression expressionEntity = new Expression();
        String expression = sc.next();
        try {
            expressionEntity.setExpression(Validator.validate(expression));
            double result = Double.parseDouble(engine.eval(expressionEntity.getExpression()).toString());
            expressionEntity.setResult(result);
        }
        catch (WrongExpressionFormat e) {
            System.out.println(e.getMessage());
            return;
        } catch (ScriptException e) {
            System.out.println("CalculatingError: " +  e.getMessage());
            return;
        }
        expressionService.save(expressionEntity);
        System.out.println("Successfully added " + expressionEntity);
    }

    private static void printSearchResult(List searchResult){
        System.out.println("Was found " + searchResult.size() + " expressions:");
        searchResult.forEach(System.out::println);
    }
}
