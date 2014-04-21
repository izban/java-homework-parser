package expression;

import exceptions.MyException;

public class UnaryNot extends UnaryOperator {
    public UnaryNot(Expression3 x) {
        super(x);
    }
    
    protected int evalImpl(int a) throws MyException {
        return ~a;
    }
}
