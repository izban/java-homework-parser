package expression;

import exceptions.MyException;
import exceptions.OverflowException;

public class Add extends BinaryOperator {

    public Add(Expression3 l, Expression3 r) {
        super(l, r);
    }

    protected int evalImpl(int a, int b) throws MyException {
        if (b > 0 && a > Integer.MAX_VALUE - b || b < 0 && a < Integer.MIN_VALUE - b) {
            throw new OverflowException("overflow");
        }
        return a + b;
    }
}
