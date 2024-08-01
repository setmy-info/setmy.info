// <editor-fold defaultstate="collapsed" desc="Hidden part">
package info.setmy.examples.lessons.level2;

import info.setmy.examples.junit5.JUnit5CollectedExamplesTest;
import java.util.ArrayList;
import static java.util.Collections.unmodifiableList;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class Lesson6StreamsTest {

    private final static Logger log = LogManager.getLogger(Lesson6StreamsTest.class);
// </editor-fold>

    private class Person {

        private String name;

        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
            log.info("4. CREATING {}, {}", name, age);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" + "name=" + name + ", age=" + age + '}';
        }
    }

    private static boolean inAge(Person person) {
        log.info("2. FILTER: {}", person);
        return person.getAge() < 24;
    }

// <editor-fold defaultstate="collapsed" desc="Hidden part">
    @Test
    void f() {
// </editor-fold>

        List<Person> personList = getPersonList();

        log.info("Initial person list: {}", personList);
        List<Person> filteredPersonList = personList.stream()
                .filter(person -> {
                    log.info("1. FILTER: {}", person);
                    return person.getAge() > 13;
                })
                .filter(Lesson6StreamsTest::inAge)
                .map(person -> {
                    log.info("3. MAP: {}", person);
                    return new Person(person.getName(), person.getAge());
                })
                .collect(Collectors.toUnmodifiableList());
        log.info("Final person list: {}", filteredPersonList);

        Person foundPerson = getPersonList().stream()
                .filter(person -> "Caesar, Irving".equalsIgnoreCase(person.getName()))
                .findFirst()
                .orElse(null);
        assertEquals(foundPerson.getAge(), 15);

        long number = getPersonList().stream()
                .filter(person -> person.getAge() > 5)
                .filter(person -> person.getAge() > 10)
                .count();
        assertEquals(number, 3);

        String name = getPersonList().stream()
                .filter(person -> "Caesar, Irving".equalsIgnoreCase(person.getName()))
                .map(Person::getName) //convert stream to String
                .findAny()
                .orElse("");
        assertEquals(name, "Caesar, Irving");

        List<String> names = getPersonList().stream()
                .map(Person::getName)
                .collect(Collectors.toList());
        assertArrayEquals(new String[]{"Aaron, Hank", "Baba, Meher", "Caesar, Irving", "Dali, Salvador", "Eastwood, Clint"}, names.toArray());

// <editor-fold defaultstate="collapsed" desc="Hidden part">
    }

    List<Person> getPersonList() {
        List<Person> personList = new ArrayList<>();
        //https://simple.wikiquote.org/wiki/List_of_people_by_name
        add(personList, "Aaron, Hank", 5);
        add(personList, "Baba, Meher", 10);
        add(personList, "Caesar, Irving", 15);
        add(personList, "Dali, Salvador", 20);
        add(personList, "Eastwood, Clint", 25);
        return unmodifiableList(personList);
    }

    List<Person> add(List<Person> personList, String name, int age) {
        personList.add(new Person(name, age));
        return personList;
    }
}
// </editor-fold>

