package expression;

import exceptions.MyException;
import number.MyNumber;

public class UnaryAbs<T extends MyNumber<T>> extends UnaryOperator<T> {
    public UnaryAbs() {
        super();
    }

    public UnaryAbs(Expression3<T> e) {
        super(e, new MyFunction<T>() {
            @Override
            public T evalImpl(T a) throws MyException {
                return a.unaryAbs();
            }

            @Override
            public T evalImpl(T a, T b) throws MyException {
                return null;
            }
        });
    }
}
