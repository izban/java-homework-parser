import expression.*;
import exceptions.*;

public class ExpressionParser {
    static private String lastLexem;
    static private Lexer lexer;

    static private Expression3 parseValue() throws ParseException {
        String s = lastLexem;
        Expression3 result;
        if (s.charAt(0) >= '0' && s.charAt(0) <= '9') {
            try {
                result = new Const(Integer.parseInt(s));
            } catch (NumberFormatException e) {
                throw new ParseException("parse fail at " + Integer.toString(lexer.getLast()));
            }
        } else if (s.charAt(0) >= 'x' && s.charAt(0) <= 'z') {
            result = new Variable(s);
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

    static private Expression3 parseMultiplier() throws ParseException {
        lastLexem = lexer.next();
        String s = lastLexem;
        Expression3 result;
        if (s.equals("")) {
            throw new ParseException("parse fail at " + Integer.toString(lexer.getLast()));
        } else
        if (s.equals("~")) {
            result = new UnaryNot(parseMultiplier());
        } else
        if (s.equals("-")) {
            result = new UnaryMinus(parseMultiplier());
        } else
        if (s.equals("abs")) {
            result = new UnaryAbs(parseMultiplier());
        } else
        if (s.equals("lb")) {
            result = new UnaryLb(parseMultiplier());
        } else {
            result = parseValue();
            if (lastLexem.equals("^")) {
                result = new Pow(result, parseMultiplier());
            }
        }
        return result;
    }

    static private Expression3 parseSummand() throws ParseException {
        Expression3 left = parseMultiplier();
        while (true) {
            String s = lastLexem;
            switch (s) {
                case "*":
                    left = new Multiply(left, parseMultiplier());
                    break;
                case "/":
                    left = new Divide(left, parseMultiplier());
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

    static private Expression3 parseExpr() throws ParseException {
        Expression3 left = parseSummand();
        while (true) {
            String s = lastLexem;
            switch (s) {
                case "+": {
                    left = new Add(left, parseSummand());
                    break;
                }
                case "-": {
                    left = new Subtract(left, parseSummand());
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

    static public Expression3 parse(String s) throws MyException {
        lexer = new Lexer(s);
        Expression3 result = parseExpr();
        if (!lastLexem.equals("")) {
            throw new ParseException("parse fail at " + Integer.toString(lexer.getLast()));
        }
        return result;
    }
}
