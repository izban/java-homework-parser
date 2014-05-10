package expression;

import exceptions.MyException;
import number.MyNumber;

public class Add<T extends MyNumber<T>> extends BinaryOperator<T> {
    Add() {
        super();
    }

    public Add(Expression3<T> l, Expression3<T> r) {
        super(l, r, new MyFunction<T>() {
            @Override
            public T evalImpl(T a) throws MyException {
                return null;
            }

            @Override
            public T evalImpl(T a, T b) throws MyException {
                return a.add(b);
            }
        });
    }
}
