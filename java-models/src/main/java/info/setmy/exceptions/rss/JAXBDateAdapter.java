package info.setmy.exceptions.rss;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class JAXBDateAdapter extends XmlAdapter<String, Date> {

    public static final String DATE_FORMAT_STRING = "EEE, d MMM yyyy HH:mm:ss z";

    @Override
    public Date unmarshal(final String dateString) throws Exception {
        return newSimpleDateFormat().parse(dateString);
    }

    @Override
    public String marshal(final Date date) throws Exception {
        return newSimpleDateFormat().format(date);
    }

    private SimpleDateFormat newSimpleDateFormat() {
        return new SimpleDateFormat(DATE_FORMAT_STRING);
    }
}
