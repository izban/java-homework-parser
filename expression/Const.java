package expression;

import exceptions.MyException;
import number.MyNumber;

public class Const<T extends MyNumber<T>> implements Expression3<T> {
    private final T value;

    public Const(T x) {
        value = x;
    }

    public T evaluate(T x, T y, T z) throws MyException {
        return value;
    }
}
