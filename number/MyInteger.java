package number;

import exceptions.*;
import expression.Const;
import expression.Multiply;

/**
 * Created with IntelliJ IDEA.
 * User: izban
 * Date: 21.04.14
 * Time: 17:18
 * To change this template use File | Settings | File Templates.
 */
public class MyInteger implements MyNumber<MyInteger> {
    final public int value;

    public MyInteger() {
        value = 0;
    }

    public MyInteger(int value) {
        this.value = value;
    }

    @Override
    public MyInteger Add(MyInteger x) throws OverflowException {
        int a = value, b = x.value;
        return new MyInteger(a + b);
    }

    @Override
    public MyInteger Subtract(MyInteger x) throws OverflowException {
        int a = value, b = x.value;
        return new MyInteger(a - b);
    }

    @Override
    public MyInteger Multiply(MyInteger x) throws OverflowException {
        int a = value, b = x.value;
        int res = a * b;
        return new MyInteger(a * b);
    }

    int mul(int a, int b) throws OverflowException {
        int res = a * b;
        return res;
    }

    @Override
    public MyInteger Divide(MyInteger x) throws OverflowException, DivisionByZeroException {
        int a = value, b = x.value;
        if (b == 0) {
            throw new DivisionByZeroException("division by zero");
        }
        return new MyInteger(a / b);
    }

    @Override
    public MyInteger UnaryAbs() throws OverflowException {
        int a = value;
        return new MyInteger(Math.abs(a));
    }

    @Override
    public MyInteger UnaryNot() {
        int a = value;
        return new MyInteger(~a);
    }

    @Override
    public MyInteger UnaryMinus() throws OverflowException {
        int a = value;
        return new MyInteger(-a);
    }

    @Override
    public int compareTo(MyInteger x) {
        return Integer.compare(value, x.value);
    }

    @Override
    public MyInteger UnaryLb() throws LogException {
        int x = value;
        if (x <= 0) {
            throw new LogException("log error");
        }
        int ans = 0;
        while (x > 1) {
            x = x / 2;
            ans++;
        }
        return new MyInteger(ans);
    }

    int pow(int a, int b) throws OverflowException {
        if (b == 0) return 1;
        int result = pow(a, b / 2);
        result = mul(result, result);
        if (b % 2 == 1) result = mul(result, a);
        return result;
    }

    @Override
    public MyInteger Pow(MyInteger x) throws OverflowException {
        int a = value, b = x.value;
        if (b < 0 || a == 0 && b == 0) {
            throw new OverflowException("pow error");
        }
        return new MyInteger(pow(a, b));
    }

    @Override
    public MyInteger parse(String s, int pos) throws ParseException {
        int result;
        try {
            result = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new ParseException("parse fail at " + Integer.toString(pos));
        }
        return new MyInteger(result);
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}