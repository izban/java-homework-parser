package expression;

import exceptions.MyException;
import exceptions.OverflowException;
import number.MyNumber;

public class UnaryAbs<T extends MyNumber<T>> extends UnaryOperator<T> {
    public UnaryAbs(Expression3<T> x) {
        super(x);
    }
    
    protected T evalImpl(T a) throws MyException {
        return a.UnaryAbs();
    }
}
