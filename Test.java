import exceptions.MyException;
import expression.Expression3;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: izban
 * Date: 14.04.14
 * Time: 19:10
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    static String doTest(String s, int x, int y, int z) {
        try {
            return Integer.toString(ExpressionParser.parse(s).evaluate(x, y, z));
        } catch (MyException e) {
            return (e.getMessage());
        } catch (Exception e) {
            return "";
        }
    }

    static void test(String s, int x, int y, int z) {
        System.out.println(doTest(s, x, y, z));
    }

    static void myTests() {
        test("x", 1, 1, 1);
        test("", 0, 0, 0);
        test("1 1", 1, 1, 1);
        test("x  *  \n  z + -y", 1, 2, 3);
        test("1000*1000*1000*x", 1, 0, 0);
        test("1000*1000*1000*x", 2, 0, 0);
        test("1000*1000*1000*x", 3, 0, 0);
        test("x / ----y", 0, 0, 1);
        test("x / y", Integer.MIN_VALUE, -1, 0);
        test("x*x*x      *   x*x*x*x * x * x *x", 0, 1, 1);
        test("x*x*x      *   x*x*x*x * x * x *x", 1, 1, 1);
        test("x*x*x      *   x*x*x*x * x * x *x", 2, 1, 1);
        test("x*x*x      *   x*x*x*x * x * x *x", 8, 1, 1);
        test("x*x*x      *   x*x*x*x * x * x *x", 9, 1, 1);
        test("x*x*x      *   x*x*x*x * x * x *x", 10, 1, 1);
        test("x*x*x      *   x*x*x*x * x * x * 16 + 0 * z", -8, 1, Integer.MIN_VALUE);
        test("x+y +      0 * x * x * x * x * y + -z", Integer.MIN_VALUE, Integer.MAX_VALUE, -1);
        test("3000000000", 0, 0, 0);
        test("00-0000000000000010", 0, 0, 0);
        test("(1)-(-(-x*-2))/(y-(--4))", 50, 4, 0);
        test("(1)-(-(-x*-2))/(y-(--4))", 50, -10, 0);
        test("(5", 0, 0, 0);
        test("5)", 0, 0, 0);
        test("(5)", 0, 0, 0);
        test("(54)) * -1+(0", 0, 0, 0);
        test("(1))", 0, 0, 0);
        test("0x1", 0, 0, 0);
        test("(-1+" + (Integer.MIN_VALUE + 1) + ")" + "*-1", 0, 0, 0);
    }

    public static void main(String[] args) {
        myTests();
        /*System.out.println(test("(-1+" + (Integer.MIN_VALUE + 1) + ")" + "*-1", 0, 0, 0));
        System.out.println(test("x^y^z", 2, 3, 3));
        try {
            PrintWriter out = new PrintWriter(new File("result.out"));
            for (int x = 0; x < 100; x++) {
                for (int y = 0; y < 100; y++) {
                    out.print(test("x^5 + y * lb (x^2 + 3)", x, y, 0) + " ");
                }
                out.println();
            }
        } catch (IOException e) {

        } */
    }
}
