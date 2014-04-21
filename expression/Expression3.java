package expression;

import exceptions.MyException;
import exceptions.OverflowException;
import number.MyNumber;

public interface Expression3<T extends MyNumber<T>> {
    public T evaluate(T x, T y, T z) throws MyException;
}
