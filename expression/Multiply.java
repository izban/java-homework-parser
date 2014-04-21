package expression;

import exceptions.MyException;
import exceptions.OverflowException;

public class Multiply extends BinaryOperator {

    public Multiply() {
        super();
    }

    public Multiply(Expression3 l, Expression3 r) {
        super(l, r);
    }

    public int Mul(int a, int b) throws MyException {
        return evalImpl(a, b);
    }

    protected int evalImpl(int a, int b) throws MyException {
        int res = a * b;
        if (a == Integer.MIN_VALUE && b == -1 || b != 0 && res / b != a) {
            throw new OverflowException("overflow");
        }
        return a * b;
    }
}
