package expression;

import exceptions.MyException;
import exceptions.OverflowException;
import number.MyNumber;

public class Subtract<T extends MyNumber<T>> extends BinaryOperator<T> {

    public Subtract(Expression3<T> l, Expression3<T> r) {
        super(l, r);
    }

    protected T evalImpl(T a, T b) throws MyException {
        return a.Subtract(b);
    }
}
