package expression;

import exceptions.MyException;
import number.MyNumber;

public class UnaryLb<T extends MyNumber<T>> extends UnaryOperator<T> {
    public UnaryLb(Expression3<T> x) {
        super(x);
    }

    protected T evalImpl(T a) throws MyException {
        return a.unaryLb();
    }
}
