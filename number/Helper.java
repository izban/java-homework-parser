package number;

import exceptions.ParseException;
import expression.*;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: izban
 * Date: 05.05.14
 * Time: 9:52
 * To change this template use File | Settings | File Templates.
 */
public abstract class Helper<T extends MyNumber<T>> {
    static HashMap<String, Integer> prior;
    static HashMap<String, Integer> operands;
    static HashMap<String, Boolean> isLeft;

    {
        prior = new HashMap<>();
        operands = new HashMap<>();
        isLeft = new HashMap<>();
    }

    {
        prior.put("*", 1);
        operands.put("*", 2);
        isLeft.put("*", true);
    }

    {
        prior.put("/", 1);
        operands.put("/", 2);
        isLeft.put("/", true);
    }

    {
        prior.put("+", 0);
        operands.put("+", 2);
        isLeft.put("+", true);
    }

    {
        prior.put("-", 0);
        operands.put("-", 2);
        isLeft.put("-", true);
    }

    {
        prior.put("^", 2);
        operands.put("^", 2);
        isLeft.put("^", false);
    }

    {
        prior.put("~", 3);
        operands.put("~", 1);
    }

    {
        prior.put("not", 3);
        operands.put("not", 1);
    }

    {
        prior.put("abs", 3);
        operands.put("abs", 1);
    }

    {
        prior.put("-un", 3);
        operands.put("-un", 1);
    }

    {
        prior.put(")", -1);
    }

    public abstract T parse(String s) throws ParseException;

    public static boolean isVariable(String s) {
        return !prior.containsKey(s);
    }

    public static int getPrior(String s) throws ParseException {
        if (!prior.containsKey(s)) {
            throw new ParseException();
        }
        return prior.get(s);
    }

    public static int getOperands(String s) throws ParseException {
        if (!operands.containsKey(s)) {
            throw new ParseException();
        }
        return operands.get(s);
    }

    public static boolean isLeft(String s) throws ParseException {
        if (!isLeft.containsKey(s)) {
            throw new ParseException();
        }
        return isLeft.get(s);
    }

    public Expression3<T> applyFunction(String s, Expression3<T> left, Expression3<T> right) throws ParseException {
        switch (s) {
            case "+":
                return new Add<>(left, right);
            case "-":
                return new Subtract<>(left, right);
            case "*":
                return new Multiply<>(left, right);
            case "/":
                return new Divide<>(left, right);
            case "^":
                return new Pow<>(left, right);
            default:
                throw new ParseException();
        }
    }

    public Expression3<T> applyFunction(String s, Expression3<T> expr) throws ParseException {
        switch (s) {
            case "~":
                return new UnaryNot<T>(expr);
            case "-un":
                return new UnaryMinus<T>(expr);
            case "abs":
                return new UnaryAbs<T>(expr);
            case "lb":
                return new UnaryLb<T>(expr);
            default:
                throw new ParseException();
        }
    }
}
