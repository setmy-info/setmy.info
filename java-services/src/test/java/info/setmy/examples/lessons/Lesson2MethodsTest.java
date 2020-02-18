// <editor-fold defaultstate="collapsed" desc="Hidden part">
package info.setmy.examples.lessons;

import org.junit.jupiter.api.Test;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class Lesson2MethodsTest {

    @Test
    void f() {
// </editor-fold>

        /**
         * Executed order is from top to bottom
         */
        int a = 2;
        int b = 3;
        int result = f(a, b);
        show(result);

        // another form of that
        show(f(2, 3));

        // more like real world programming
        a = getNumberA(); // gives always 2, but in real world that number can come from arithmetic, DB or complex logic from everithing etc
        b = getNumberB(); // gives always 3, but in real world that number can come from arithmetic, DB or complex logic from everithing etc
        result = f(a, b);
        show(result);

        // Same
        show(f(getNumberA(), getNumberB()));

// <editor-fold defaultstate="collapsed" desc="Hidden part">
    }

    int f(int a, int b) {
        int sum = a + b;
        return sum;
    }

    void show(int number) {
        System.out.println("Number is: " + number);
    }

    int getNumberA() {
        return 2;
    }

    int getNumberB() {
        return 3;
    }
}
// </editor-fold>
