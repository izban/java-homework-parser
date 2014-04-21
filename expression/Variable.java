package expression;

import exceptions.MyException;

public class Variable implements Expression3 {
    private final String name;

    public Variable(String s) {
        name = s;
    }

    public int evaluate(int x, int y, int z) throws MyException {
        switch (name) {
            case "x": return x;
            case "y": return y;
            case "z": return z;
        }
        assert false;
        return 0;
    }
}
