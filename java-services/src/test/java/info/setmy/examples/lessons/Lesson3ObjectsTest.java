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

        String getFullName() {
            String concatenated = firstName + " " + lastName;
            return concatenated;
        }
    }

    void example() {
        Person person = new Person();
        person.firstName = "Peeter";
        person.lastName = "Meeter";

        System.out.println("Full name: " + person.getFullName());
    }

// <editor-fold defaultstate="collapsed" desc="Hidden part">
}
// </editor-fold>
