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
    T add(T x) throws OverflowException;
    T subtract(T x) throws OverflowException;
    T multiply(T x) throws OverflowException;
    T divide(T x) throws OverflowException, DivisionByZeroException;
    T unaryAbs() throws OverflowException;
    T unaryNot();
    T unaryMinus() throws OverflowException;
    T unaryLb() throws LogException;
    T pow(T x) throws OverflowException;
    T parse(String s, int pos) throws ParseException;
    int compareTo(T a);
    String toString();
}
