package expression;

import exceptions.MyException;
import number.MyNumber;

/**
 * Created with IntelliJ IDEA.
 * User: izban
 * Date: 10.05.14
 * Time: 9:27
 * To change this template use File | Settings | File Templates.
 */
public interface MyFunction<T extends MyNumber<T>> {
    public T evalImpl(T a) throws MyException;
    public T evalImpl(T a, T b) throws MyException ;
}
