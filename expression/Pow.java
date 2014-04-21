package expression;

import exceptions.MyException;
import exceptions.OverflowException;
import number.MyInteger;
import number.MyNumber;

public class Pow<T extends MyNumber<T>> extends BinaryOperator<T> {

    public Pow(Expression3<T> l, Expression3<T> r) {
        super(l, r);
    }

    protected T evalImpl(T a, T b) throws MyException {
        return a.Pow(b);
    }
}
