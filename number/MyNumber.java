package number;

import exceptions.DivisionByZeroException;
import exceptions.LogException;
import exceptions.OverflowException;
import exceptions.ParseException;

/**
 * Created with IntelliJ IDEA.
 * User: izban
 * Date: 21.04.14
 * Time: 17:11
 * To change this template use File | Settings | File Templates.
 */
public interface MyNumber<T> {
    T Add(T x) throws OverflowException;
    T Subtract(T x) throws OverflowException;
    T Multiply(T x) throws OverflowException;
    T Divide(T x) throws OverflowException, DivisionByZeroException;
    T UnaryAbs() throws OverflowException;
    T UnaryNot();
    T UnaryMinus() throws OverflowException;
    T UnaryLb() throws LogException;
    T Pow(T x) throws OverflowException;
    T parse(String s, int pos) throws ParseException;
    int compareTo(T a);
    String toString();
}
