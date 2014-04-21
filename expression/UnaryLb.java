package expression;

import exceptions.LogException;
import exceptions.MyException;
import exceptions.OverflowException;

public class UnaryLb extends UnaryOperator {
    public UnaryLb(Expression3 x) {
        super(x);
    }

    int lb(int x) throws MyException {
        if (x <= 0) {
            throw new LogException("log error");
        }
        int ans = 0;
        while (x > 1) {
            x = x / 2;
            ans++;
        }
        return ans;
    }

    protected int evalImpl(int a) throws MyException {
        if (a == Integer.MIN_VALUE) {
            throw new OverflowException("overflow");
        }
        return lb(a);
    }
}
