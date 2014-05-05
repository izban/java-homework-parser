import expression.*;
import exceptions.*;
import number.Helper;
import number.MyInteger;
import number.MyNumber;

public class ExpressionParser<T extends MyNumber<T>> {
    private String lastLexem;
    private Lexer lexer;
    private Helper<T> helper;

    /*private Expression3<T> parseValue() throws ParseException {
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
    }       */


    //-1 )
    // 0 +- // left
    // 1 */ // left
    // 2 ^  // right
    // 3 unary functions; const; variables; (; reading

    boolean isConst(String s) {
        return s.charAt(0) >= '0' && s.charAt(0) <= '9';
    }

    boolean isVariable(String s) {
        return Helper.isVariable(s);
    }

    private Expression3<T> parseExpr(int h) throws ParseException {
        try {
            if (h < 3) {
                Expression3<T> left = parseExpr(h + 1);
                String s = lastLexem;
                if (s.equals("") || Helper.getPrior(s) < h) {
                    return left;
                }
                if (h == 0 || h == 1) {
                    while (true) {
                        s = lastLexem;
                        if (s.equals("") || Helper.getPrior(s) < h) {
                            return left;
                        }
                        left = helper.applyFunction(s, left, parseExpr(h + 1));
                    }
                } else {
                    return helper.applyFunction(s, left, parseExpr(h));
                }
            } else {
                lastLexem = lexer.next();
                String s = lastLexem;
                if (s.equals("-")) {
                    s = "-un";
                }
                if (s.equals("")) {
                    throw new ParseException();
                }
                Expression3<T> result;

                if (isConst(s)) {
                    result = new Const<>(helper.parse(s));
                    lastLexem = lexer.next();
                } else
                if (s.equals("(")) {
                    result = parseExpr(0);
                    lastLexem = lexer.next();
                } else {
                if (isVariable(s)) {
                    result = new Variable<>(s);
                    lastLexem = lexer.next();
                } else
                    result = helper.applyFunction(s, parseExpr(h));
                }

                return result;
            }
        } catch (ParseException e) {
            throw new ParseException("parse error at " + lexer.getLast());
        }
    }

    private Expression3<T> run(String s, Helper<T> helper) throws MyException {
        this.helper = helper;
        lexer = new Lexer(s);
        Expression3<T> result = parseExpr(0);
        if (!lastLexem.equals("")) {
            throw new ParseException("parse fail at " + Integer.toString(lexer.getLast()));
        }
        return result;
    }

    static public <T extends MyNumber<T>> Expression3<T> parse(String s, Helper<T> helper) throws MyException {
        return new ExpressionParser<T>().run(s, helper);
    }
}