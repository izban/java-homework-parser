package expression;

import exceptions.MyException;
import exceptions.OverflowException;

public class Pow extends BinaryOperator {

    public Pow(Expression3 l, Expression3 r) {
        super(l, r);
    }

    int pow(int a, int b) throws MyException {
        if (b == 0) return 1;
        int result = pow(a, b / 2);
        result = new Multiply().Mul(result, result);
        if (b % 2 == 1) result = new Multiply().Mul(result, a);
        return result;
    }

    protected int evalImpl(int a, int b) throws MyException {
        if (b < 0 || a == 0 && b == 0) {
            throw new OverflowException("pow error");
        }
        return pow(a, b);
    }
}
