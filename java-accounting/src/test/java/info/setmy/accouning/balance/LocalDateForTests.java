package info.setmy.accouning.balance;

import java.time.LocalDate;
import static java.time.format.DateTimeFormatter.ofPattern;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class LocalDateForTests {

    public static final String DATE_FORMAT = "dd.MM.yyyy";

    public static LocalDate parseLocalDate(final String dateString) {
        return LocalDate.parse(dateString, ofPattern(DATE_FORMAT));
    }
}
