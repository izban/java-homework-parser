package expression;

import exceptions.MyException;
import exceptions.OverflowException;

public class UnaryMinus extends UnaryOperator {
    public UnaryMinus(Expression3 x) {
        super(x);
    }
    
    protected int evalImpl(int a) throws MyException {
        if (a == Integer.MIN_VALUE) {
            throw new OverflowException("overflow");
        }
        return -a;
    }
}
