package number;

import exceptions.DivisionByZeroException;
import exceptions.LogException;
import exceptions.OverflowException;
import exceptions.ParseException;

/**
 * Created with IntelliJ IDEA.
 * User: izban
 * Date: 21.04.14
 * Time: 17:18
 * To change this template use File | Settings | File Templates.
 */
public class MyDouble implements MyNumber<MyDouble> {
    private final double eps = 1e-9;

    final public double value;

    public MyDouble() {
        value = 0;
    }

    public MyDouble(double value) {
        this.value = value;
    }

    @Override
    public MyDouble Add(MyDouble x) throws OverflowException {
        double a = value, b = x.value;
        return new MyDouble(a + b);
    }

    @Override
    public MyDouble Subtract(MyDouble x) throws OverflowException {
        double a = value, b = x.value;
        return new MyDouble(a - b);
    }

    @Override
    public MyDouble Multiply(MyDouble x) throws OverflowException {
        double a = value, b = x.value;
        return new MyDouble(a * b);
    }

    @Override
    public MyDouble Divide(MyDouble x) throws OverflowException, DivisionByZeroException {
        double a = value, b = x.value;
        if (Math.abs(b) < eps) {
            //assert false;
            throw new DivisionByZeroException("division by zero");
            //return new MyDouble();
        }
        return new MyDouble(a / b);
    }

    @Override
    public MyDouble UnaryAbs() throws OverflowException {
        return new MyDouble(Math.abs(value));
    }

    @Override
    public MyDouble UnaryNot() {
        return new MyDouble(value); // will never happen
    }

    @Override
    public MyDouble UnaryMinus() throws OverflowException {
        return new MyDouble(-value);
    }

    @Override
    public int compareTo(MyDouble x) {
        return Double.compare(value, x.value);
    }

    @Override
    public MyDouble UnaryLb() throws LogException {
        double x = value;
        if (x < eps) {
            throw new LogException("log error");
        }
        x = Math.log(x);
        return new MyDouble(x);
    }

    int pow(int a, int b) throws OverflowException {
        if (b == 0) return 1;
        int result = pow(a, b / 2);
        result = result * result;
        if (b % 2 == 1) result = result * a;
        return result;
    }

    @Override
    public MyDouble Pow(MyDouble x) throws OverflowException {
        double a = value, b = x.value;
        if (b < -eps || Math.abs(a) < eps && Math.abs(b) < eps) {
            //assert false;
            //return new MyDouble();
            throw new OverflowException("overflow");
        }
        return new MyDouble(Math.pow(a, b));
    }

    @Override
    public MyDouble parse(String s, int pos) throws ParseException {
        double result;
        try {
            result = Double.parseDouble(s);
        } catch (NumberFormatException e) {
            throw new ParseException("parse fail at " + Integer.toString(pos));
        }
        return new MyDouble(result);
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }
}