package info.setmy.models.storage;

import info.setmy.models.storage.DirectoryStructurePattern.DirectoryStructureDepth;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class FolderStructurePatternTest {

    DirectoryStructurePattern folderStructurePattern;

    @BeforeEach
    public void setUp() {
        java.util.TimeZone.setDefault(TimeZone.getTimeZone("Europe/Tallinn"));
        folderStructurePattern = new DirectoryStructurePattern();
    }

    @Test
    public void justCreatdShouldHaveEmptyString() {
        assertThat(folderStructurePattern.toString(), is(equalTo("")));
    }

    @Test
    public void fillingYear() {
        folderStructurePattern.setYear(2018);
        assertThat(folderStructurePattern.toString(), is(equalTo("2018")));
    }

    @Test
    public void fillingYearAndMonth() {
        folderStructurePattern.setYear(2018).setMonth(12);
        assertThat(folderStructurePattern.toString(), is(equalTo("2018/12")));
    }

    @Test
    public void fillingYearAndMonthAndDay() {
        folderStructurePattern.setYear(2018).setMonth(12).setDay(31);
        assertThat(folderStructurePattern.toString(), is(equalTo("2018/12/31")));
    }

    @Test
    public void fillingYearAndMonthAndDayAndHour() {
        folderStructurePattern.setYear(2018).setMonth(12).setDay(31).setHour(23);
        assertThat(folderStructurePattern.toString(), is(equalTo("2018/12/31/23")));
    }

    @Test
    public void fillingYearAndMonthAndDayAndHourAndMinute() {
        folderStructurePattern.setYear(2018).setMonth(12).setDay(31).setHour(23).setMinute(59);
        assertThat(folderStructurePattern.toString(), is(equalTo("2018/12/31/23/59")));
    }

    @Test
    public void fillingYearAndMonthAndDayAndHourAndMinuteAndSecond() {
        folderStructurePattern.setYear(2018).setMonth(12).setDay(31).setHour(23).setMinute(59).setSecond(59);
        assertThat(folderStructurePattern.toString(), is(equalTo("2018/12/31/23/59/59")));
    }

    @Test
    public void fillingYearAndMonthAndDayAndHourAndMinuteAndSecondAndMilliSecond() {
        folderStructurePattern.setYear(2018).setMonth(12).setDay(31).setHour(23).setMinute(59).setSecond(59).setMilliSecond(1234);
        assertThat(folderStructurePattern.toString(), is(equalTo("2018/12/31/23/59/59/1234")));
    }

    @Test
    public void addDate() {
        folderStructurePattern.setDate(getDate(), DirectoryStructureDepth.MILLISECOND);
        assertThat(folderStructurePattern.toString(), is(equalTo("2017/12/31/8/18/10/252")));
    }

    @Test
    public void addFull() {
        folderStructurePattern = new DirectoryStructurePattern("owner", "subOwner");
        folderStructurePattern.setDate(getDate(), DirectoryStructureDepth.MILLISECOND);
        assertThat(folderStructurePattern.toString(), is(equalTo("owner/subOwner/2017/12/31/8/18/10/252")));
    }

    @Test
    public void addFull2() {
        folderStructurePattern = new DirectoryStructurePattern(null, "subOwner");
        folderStructurePattern.setDate(getDate(), DirectoryStructureDepth.MILLISECOND);
        assertThat(folderStructurePattern.toString(), is(equalTo("subOwner/2017/12/31/8/18/10/252")));
    }

    @Test
    public void addFull3() {
        folderStructurePattern = new DirectoryStructurePattern("owner", null);
        folderStructurePattern.setDate(getDate(), DirectoryStructureDepth.MILLISECOND);
        assertThat(folderStructurePattern.toString(), is(equalTo("owner/2017/12/31/8/18/10/252")));
    }

    Date getDate() {
        final String DATE_FORMAT_STRING = "dd.MM.yyyy hh:mm:ss.SSS";
        final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_STRING);
        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Tallinn"));
        final String dateInString = "31.12.2017 08:18:10.252";
        try {
            return dateFormat.parse(dateInString);
        } catch (ParseException ex) {
        }
        return null;
    }
}
