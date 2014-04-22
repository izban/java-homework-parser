import exceptions.MyException;
import expression.Expression3;
import number.MyBigInteger;
import number.MyDouble;
import number.MyInteger;
import number.MyNumber;

/**
 * Created with IntelliJ IDEA.
 * User: izban
 * Date: 21.04.14
 * Time: 23:11
 * To change this template use File | Settings | File Templates.
 */
public class GenericParser {

    <T extends MyNumber<T>> void test(String t, T copy) throws MyException {
        Expression3<T> expr = ExpressionParser.parse(t, copy);
        for (int x = -100; x <= 100; x++) {
            for (int y = -100; y <= 100; y++) {
                try {
                    System.out.print(expr.evaluate(copy.parse(Integer.toString(x), 0), copy.parse(Integer.toString(y), 0), copy.parse("0", 0)).toString() + " ");
                } catch (MyException e) {
                    System.out.print("error ");
                } catch (Exception e) {

                }
            }
            System.out.println();
        }
    }

    void run(String[] args) {
        try {
            String type = args[0];
            if (type.equals("-i")) {
                test(args[1], new MyInteger());
            } else if (type.equals("-d")) {
                test(args[1], new MyDouble());
            } else if (type.equals("-bi")) {
                test(args[1], new MyBigInteger());
            }
        } catch (Exception e) {

        }

    }

    public static void main(String[] args) {
        new GenericParser().run(args);
    }
}
