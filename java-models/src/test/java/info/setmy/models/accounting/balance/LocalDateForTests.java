package info.setmy.models.accounting.balance;

import java.time.LocalDateTime;
import static java.time.format.DateTimeFormatter.ofPattern;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class LocalDateForTests {

    public static final String DATE_TIME_FORMAT = "dd.MM.yyyy HH:mm:ss";

    public static LocalDateTime parseLocalDate(final String dateString) {
        return LocalDateTime.parse(dateString, ofPattern(DATE_TIME_FORMAT));
    }
}
