package expression;

import exceptions.MyException;
import number.MyNumber;

public class UnaryNot<T extends MyNumber<T>> extends UnaryOperator<T> {
    public UnaryNot(Expression3<T> x) {
        super(x);
    }
    
    protected T evalImpl(T a) throws MyException {
        return a.UnaryNot();
    }
}
