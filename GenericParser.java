import exceptions.MyException;
import expression.Expression3;
import number.MyBigInteger;
import number.MyDouble;
import number.MyInteger;
import sun.org.mozilla.javascript.ast.Yield;

/**
 * Created with IntelliJ IDEA.
 * User: izban
 * Date: 21.04.14
 * Time: 23:11
 * To change this template use File | Settings | File Templates.
 */
public class GenericParser {
    public static void main(String[] args) {
        String type = args[0];
        try {
            if (type.equals("-i")) {
                Expression3<MyInteger> expr = ExpressionParser.parse(args[1], new MyInteger());
                for (int x = -100; x <= 100; x++) {
                    for (int y = -100; y <= 100; y++) {
                        try {
                            System.out.print(expr.evaluate(new MyInteger(x), new MyInteger(y), new MyInteger(0)).toString() + " ");
                        } catch (MyException e) {
                            System.out.print("error ");
                        } catch (Exception e) {

                        }
                    }
                    System.out.println();
                }
            } else if (type.equals("-d")) {
                Expression3<MyDouble> expr = ExpressionParser.parse(args[1], new MyDouble());
                for (int x = -100; x <= 100; x++) {
                    for (int y = -100; y <= 100; y++) {
                        try {
                            System.out.print(expr.evaluate(new MyDouble(x), new MyDouble(y), new MyDouble(0)).toString() + " ");
                        } catch (MyException e) {
                            System.out.print("error ");
                        } catch (Exception e) {

                        }
                    }
                    System.out.println();
                }
            } else if (type.equals("-bi")) {
                Expression3<MyBigInteger> expr = ExpressionParser.parse(args[1], new MyBigInteger());
                for (int x = -100; x <= 100; x++) {
                    for (int y = -100; y <= 100; y++) {
                        try {
                            System.out.print(expr.evaluate(new MyBigInteger(x), new MyBigInteger(y), new MyBigInteger(0)).toString() + " ");
                        } catch (MyException e) {
                            System.out.print("error ");
                        } catch (Exception e) {

                        }
                    }
                    System.out.println();
                }
            }
        } catch (Exception e) {

        }
    }
}
