package info.setmy.examples.cucumber;

import io.cucumber.cucumberexpressions.Transformer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class DateMapper implements Transformer {

    @Override
    public LocalDate transform(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
