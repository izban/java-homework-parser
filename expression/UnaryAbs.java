package expression;

import exceptions.MyException;
import exceptions.OverflowException;

public class UnaryAbs extends UnaryOperator {
    public UnaryAbs(Expression3 x) {
        super(x);
    }
    
    protected int evalImpl(int a) throws MyException {
        if (a == Integer.MIN_VALUE) {
            throw new OverflowException("overflow");
        }
        return Math.abs(a);
    }
}
