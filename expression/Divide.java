package expression;

import exceptions.DivisionByZeroException;
import exceptions.MyException;
import exceptions.OverflowException;

public class Divide extends BinaryOperator {

    public Divide(Expression3 l, Expression3 r) {
        super(l, r);
    }

    protected int evalImpl(int a, int b) throws MyException {
        if (b == 0) {
            throw new DivisionByZeroException("division by zero");
        }
        if (a == Integer.MIN_VALUE && b == -1) {
            throw new OverflowException("overflow");
        }
        return a / b;
    }
}
