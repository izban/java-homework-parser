import expression.*;
import exceptions.*;
import number.MyInteger;
import number.MyNumber;

public class ExpressionParser<T extends MyNumber<T>> {
    private String lastLexem;
    private Lexer lexer;
    private T copy;

    private Expression3<T> parseValue() throws ParseException {
        String s = lastLexem;
        Expression3<T> result;
        if (s.charAt(0) >= '0' && s.charAt(0) <= '9') {
            try {
                result = new Const<>(copy.parse(s, lexer.getLast()));
            } catch (NumberFormatException e) {
                throw new ParseException("parse fail at " + Integer.toString(lexer.getLast()));
            }
        } else if (s.charAt(0) >= 'x' && s.charAt(0) <= 'z') {
            result = new Variable<>(s);
        } else if (s.equals("(")) {
            result = parseExpr();
            if (!lastLexem.equals(")")) {
                throw new ParseException("parse fail at " + Integer.toString(lexer.getLast()));
            }
        } else {
            throw new ParseException("parse fail at " + Integer.toString(lexer.getLast()));
        }
        lastLexem = lexer.next();
        return result;
    }

    private Expression3<T> parseMultiplier() throws ParseException {
        lastLexem = lexer.next();
        String s = lastLexem;
        Expression3<T> result;
        if (s.equals("")) {
            throw new ParseException("parse fail at " + Integer.toString(lexer.getLast()));
        } else
        if (s.equals("~")) {
            result = new UnaryNot<>(parseMultiplier());
        } else
        if (s.equals("-")) {
            result = new UnaryMinus<>(parseMultiplier());
        } else
        if (s.equals("abs")) {
            result = new UnaryAbs<>(parseMultiplier());
        } else
        if (s.equals("lb")) {
            result = new UnaryLb<>(parseMultiplier());
        } else {
            result = parseValue();
            if (lastLexem.equals("^")) {
                result = new Pow<>(result, parseMultiplier());
            }
        }
        return result;
    }

    private Expression3<T> parseSummand() throws ParseException {
        Expression3<T> left = parseMultiplier();
        while (true) {
            String s = lastLexem;
            switch (s) {
                case "*":
                    left = new Multiply<>(left, parseMultiplier());
                    break;
                case "/":
                    left = new Divide<>(left, parseMultiplier());
                    break;
                case ")":
                case "":
                case "+":
                case "-":
                    return left;
                default:
                    throw new ParseException("parse fail at " + Integer.toString(lexer.getLast()));
            }
        }
    }

    private Expression3<T> parseExpr() throws ParseException {
        Expression3<T> left = parseSummand();
        while (true) {
            String s = lastLexem;
            switch (s) {
                case "+": {
                    left = new Add<>(left, parseSummand());
                    break;
                }
                case "-": {
                    left = new Subtract<>(left, parseSummand());
                    break;
                }
                case ")": {
                    return left;
                }
                case "": {
                    return left;
                }
                default: {
                    throw new ParseException("parse fail at " + Integer.toString(lexer.getLast()));
                }
            }
        }
    }

    private Expression3<T> run(String s, T copy) throws MyException {
        this.copy = copy;
        lexer = new Lexer(s);
        Expression3<T> result = parseExpr();
        if (!lastLexem.equals("")) {
            throw new ParseException("parse fail at " + Integer.toString(lexer.getLast()));
        }
        return result;
    }

    static public <T extends MyNumber<T>> Expression3<T> parse(String s, T copy) throws MyException {
        return new ExpressionParser<T>().run(s, copy);
    }
}