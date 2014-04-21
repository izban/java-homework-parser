package expression;

import exceptions.MyException;
import number.MyNumber;

public abstract class UnaryOperator<T extends MyNumber<T>> implements Expression3<T> {
    protected final Expression3<T> e;

    public UnaryOperator(Expression3<T> e) {
        this.e = e;
    }

    protected abstract T evalImpl(T a) throws MyException;

    public T evaluate(T x, T y, T z) throws MyException {
       T a = e.evaluate(x, y, z);
       return evalImpl(a);
    }
}
