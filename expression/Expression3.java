package expression;

import exceptions.MyException;
import exceptions.OverflowException;

public interface Expression3 {
    public int evaluate(int x, int y, int z) throws MyException;
}
