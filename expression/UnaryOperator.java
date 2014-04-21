package expression;

import exceptions.MyException;

public abstract class UnaryOperator implements Expression3 {
    protected final Expression3 e;

    public UnaryOperator(Expression3 e) {
        this.e = e;
    }

    protected abstract int evalImpl(int a) throws MyException;

    public int evaluate(int x, int y, int z) throws MyException {
       int a = e.evaluate(x, y, z);
       return evalImpl(a);
    }
}
