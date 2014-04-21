package expression;

import exceptions.MyException;
import exceptions.OverflowException;
import number.MyNumber;

public abstract class BinaryOperator<T extends MyNumber<T>> implements Expression3<T> {
    protected final Expression3<T> left, right;

    public BinaryOperator() {
        left = null;
        right = null;
    }

    public BinaryOperator(Expression3<T> l, Expression3<T> r) {
        left = l;
        right = r;
    }

    protected abstract T evalImpl(T a, T b) throws MyException;

    public T evaluate(T x, T y, T z) throws MyException {
       T a = left.evaluate(x, y, z);
       T b = right.evaluate(x, y, z);
       return evalImpl(a, b);
    }
}
