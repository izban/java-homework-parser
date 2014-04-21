package expression;

import exceptions.DivisionByZeroException;
import exceptions.MyException;
import exceptions.OverflowException;
import number.MyNumber;

public class Divide<T extends MyNumber<T>> extends BinaryOperator<T> {

    public Divide(Expression3<T> l, Expression3<T> r) {
        super(l, r);
    }

    protected T evalImpl(T a, T b) throws MyException {
        return a.Divide(b);
    }
}
