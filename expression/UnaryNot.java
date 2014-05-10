package expression;

import exceptions.MyException;
import number.MyNumber;

public class UnaryNot<T extends MyNumber<T>> extends UnaryOperator<T> {
    public UnaryNot() {
        super();
    }

    public UnaryNot(Expression3<T> e) {
        super(e, new MyFunction<T>() {
            @Override
            public T evalImpl(T a) throws MyException {
                return a.unaryNot();
            }

            @Override
            public T evalImpl(T a, T b) throws MyException {
                return null;
            }
        });
    }
}
