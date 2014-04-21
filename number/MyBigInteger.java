package number;

import exceptions.DivisionByZeroException;
import exceptions.LogException;
import exceptions.OverflowException;
import exceptions.ParseException;

import java.math.BigInteger;

/**
 * Created with IntelliJ IDEA.
 * User: izban
 * Date: 21.04.14
 * Time: 17:18
 * To change this template use File | Settings | File Templates.
 */
public class MyBigInteger implements MyNumber<MyBigInteger> {
    final public BigInteger value;

    public MyBigInteger() {
        value = BigInteger.ZERO;
    }

    public MyBigInteger(BigInteger value) {
        this.value = value;
    }

    public MyBigInteger(int value) {
        this.value = BigInteger.valueOf(value);
    }

    @Override
    public MyBigInteger Add(MyBigInteger x) throws OverflowException {
        BigInteger a = value, b = x.value;
        return new MyBigInteger(a.add(b));
    }

    @Override
    public MyBigInteger Subtract(MyBigInteger x) throws OverflowException {
        BigInteger a = value, b = x.value;
        return new MyBigInteger(a.subtract(b));
    }

    @Override
    public MyBigInteger Multiply(MyBigInteger x) throws OverflowException {
        BigInteger a = value, b = x.value;
        return new MyBigInteger(a.multiply(b));
    }

    @Override
    public MyBigInteger Divide(MyBigInteger x) throws OverflowException, DivisionByZeroException {
        BigInteger a = value, b = x.value;
        if (b.equals(BigInteger.ZERO)) {
            throw new DivisionByZeroException("division by zero");
        }
        return new MyBigInteger(a.divide(b));
    }

    @Override
    public MyBigInteger UnaryAbs() throws OverflowException {
        return new MyBigInteger(value.abs());
    }

    @Override
    public MyBigInteger UnaryNot() {
        return new MyBigInteger(value); // never happen
    }

    @Override
    public MyBigInteger UnaryMinus() throws OverflowException {
        return new MyBigInteger(value.negate());
    }

    @Override
    public int compareTo(MyBigInteger x) {
        return value.compareTo(x.value);
    }

    @Override
    public MyBigInteger UnaryLb() throws LogException {
        BigInteger x = value;
        if (x.compareTo(BigInteger.ZERO) <= 0) {
            throw new LogException("log error");
        }
        int ans = 0;
        while (x.compareTo(BigInteger.ONE) > 0) {
            x = x.divide(BigInteger.valueOf(2));
            ans++;
        }
        return new MyBigInteger(BigInteger.valueOf(ans));
    }

    BigInteger pow(BigInteger a, BigInteger b) throws OverflowException {
        if (b.equals(BigInteger.ZERO)) return BigInteger.ONE;
        BigInteger result = pow(a, b.divide(BigInteger.valueOf(2)));
        result = result.multiply(result);
        if (!b.remainder(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) result = result.multiply(a);
        return result;
    }

    @Override
    public MyBigInteger Pow(MyBigInteger x) throws OverflowException {
        BigInteger a = value, b = x.value;
        if (b.compareTo(BigInteger.ZERO) < 0 || a.compareTo(BigInteger.ZERO) == 0 && b.compareTo(BigInteger.ZERO) == 0) {
            throw new OverflowException("pow error");
        }
        return new MyBigInteger(pow(a, b));
    }

    @Override
    public MyBigInteger parse(String s, int pos) throws ParseException {
        BigInteger result;
        try {
            result = new BigInteger(s);
        } catch (Exception e) {
            throw new ParseException("parse fail at " + Integer.toString(pos));
        }
        return new MyBigInteger(result);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}