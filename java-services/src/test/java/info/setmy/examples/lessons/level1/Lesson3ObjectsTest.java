// <editor-fold defaultstate="collapsed" desc="Hidden part">
package info.setmy.examples.lessons.level1;

import java.time.LocalDate;
import java.time.Period;
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
        LocalDate birthDate;

        String getFullName() {
            String concatenated = firstName + " " + lastName;
            return concatenated;
        }

        int getAge() {
            return Period.between(
                    birthDate,
                    LocalDate.now()
            ).getYears();
        }

        void setBirthDate(int day, int month, int year) {
            birthDate = LocalDate.of(year, month, day);
        }
    }

    void example() {
        Person person = new Person();
        person.firstName = "Peeter";
        person.lastName = "Meeter";
        person.setBirthDate(05, 12, 1976);
        System.out.println("Full name: " + person.getFullName() + ", age: " + person.getAge());
    }

// <editor-fold defaultstate="collapsed" desc="Hidden part">
}
// </editor-fold>
