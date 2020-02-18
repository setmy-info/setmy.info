// <editor-fold defaultstate="collapsed" desc="Hidden part">
package info.setmy.examples.lessons;

import java.util.Date;
import org.junit.jupiter.api.Test;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class Lesson3ObjectsTest {

    @Test
    void f() {
        example();
    }
// </editor-fold>

    class Person {

        String firstName;
        String lastName;
        Date birthDate;
    }

    void example() {
        Person person = new Person();
        person.firstName = "Peeter";
        person.lastName = "Meeter";
    }

// <editor-fold defaultstate="collapsed" desc="Hidden part">
}
// </editor-fold>
