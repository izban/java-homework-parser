package expression;

import exceptions.MyException;

public class Const implements Expression3 {
    private final int value;

    public Const(int x) {
        value = x;
    }

    public int evaluate(int x, int y, int z) throws MyException {
        return value;
    }
}
