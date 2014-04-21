package expression;

import exceptions.MyException;
import exceptions.OverflowException;
import number.MyNumber;

public class UnaryMinus<T extends MyNumber<T>> extends UnaryOperator<T> {
    public UnaryMinus(Expression3<T> x) {
        super(x);
    }
    
    protected T evalImpl(T a) throws MyException {
        return a.UnaryMinus();
    }
}
