import expression.*;
import exceptions.*;
import number.MyInteger;
import number.MyNumber;

public class ExpressionParser<T extends MyNumber<T>> {
    private String lastLexem;
    private Lexer lexer;

    private <T extends MyNumber<T>> Expression3<T> parseValue(T test) throws ParseException {
        String s = lastLexem;
        Expression3<T> result;
        if (s.charAt(0) >= '0' && s.charAt(0) <= '9') {
            try {
                result = new Const<T>(test.parse(s, lexer.getLast()));
            } catch (NumberFormatException e) {
                throw new ParseException("parse fail at " + Integer.toString(lexer.getLast()));
            }
        } else if (s.charAt(0) >= 'x' && s.charAt(0) <= 'z') {
            result = new Variable<T>(s);
        } else if (s.equals("(")) {
            result = parseExpr(test);
            if (!lastLexem.equals(")")) {
                throw new ParseException("parse fail at " + Integer.toString(lexer.getLast()));
            }
        } else {
            throw new ParseException("parse fail at " + Integer.toString(lexer.getLast()));
        }
        lastLexem = lexer.next();
        return result;
    }

    private <T extends MyNumber<T>> Expression3<T> parseMultiplier(T test) throws ParseException {
        lastLexem = lexer.next();
        String s = lastLexem;
        Expression3<T> result;
        if (s.equals("")) {
            throw new ParseException("parse fail at " + Integer.toString(lexer.getLast()));
        } else
        if (s.equals("~")) {
            result = new UnaryNot<T>(parseMultiplier(test));
        } else
        if (s.equals("-")) {
            result = new UnaryMinus<T>(parseMultiplier(test));
        } else
        if (s.equals("abs")) {
            result = new UnaryAbs<T>(parseMultiplier(test));
        } else
        if (s.equals("lb")) {
            result = new UnaryLb<T>(parseMultiplier(test));
        } else {
            result = parseValue(test);
            if (lastLexem.equals("^")) {
                result = new Pow<T>(result, parseMultiplier(test));
            }
        }
        return result;
    }

    private <T extends MyNumber<T>> Expression3<T> parseSummand(T test) throws ParseException {
        Expression3<T> left = parseMultiplier(test);
        while (true) {
            String s = lastLexem;
            switch (s) {
                case "*":
                    left = new Multiply<T>(left, parseMultiplier(test));
                    break;
                case "/":
                    left = new Divide<T>(left, parseMultiplier(test));
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

    private <T extends MyNumber<T>> Expression3<T> parseExpr(T test) throws ParseException {
        Expression3<T> left = parseSummand(test);
        while (true) {
            String s = lastLexem;
            switch (s) {
                case "+": {
                    left = new Add<T>(left, parseSummand(test));
                    break;
                }
                case "-": {
                    left = new Subtract<T>(left, parseSummand(test));
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

    private <T extends MyNumber<T>> Expression3<T> run(String s, T test) throws MyException {
        lexer = new Lexer(s);
        Expression3<T> result = parseExpr(test);
        if (!lastLexem.equals("")) {
            throw new ParseException("parse fail at " + Integer.toString(lexer.getLast()));
        }
        return result;
    }

    static public <T extends MyNumber<T>> Expression3<T> parse(String s, T test) throws MyException {
        return new ExpressionParser<T>().run(s, test);
    }
}