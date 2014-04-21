package expression;

import exceptions.MyException;
import exceptions.OverflowException;

public abstract class BinaryOperator implements Expression3 {
    protected final Expression3 left, right;

    public BinaryOperator() {
        left = null;
        right = null;
    }

    public BinaryOperator(Expression3 l, Expression3 r) {
        left = l;
        right = r;
    }

    protected abstract int evalImpl(int a, int b) throws MyException;

    public int evaluate(int x, int y, int z) throws MyException {
       int a = left.evaluate(x, y, z);
       int b = right.evaluate(x, y, z);
       return evalImpl(a, b);
    }
}
