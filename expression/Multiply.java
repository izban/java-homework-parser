package expression;

import exceptions.MyException;
import number.MyNumber;

public class Multiply<T extends MyNumber<T>> extends BinaryOperator<T> {
    Multiply() {
        super();
    }

    public Multiply(Expression3<T> l, Expression3<T> r) {
        super(l, r, new MyFunction<T>() {
            @Override
            public T evalImpl(T a) throws MyException {
                return null;
            }

            @Override
            public T evalImpl(T a, T b) throws MyException {
                return a.multiply(b);
            }
        });
    }
}
