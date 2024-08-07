package info.setmy.models.storage;

import info.setmy.models.storage.DirectoryStructureFileCreationPattern.DirectoryStructureDepth;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class FolderStructurePatternTest {

    DirectoryStructureFileCreationPattern folderStructurePattern;

    @BeforeEach
    public void setUp() {
        java.util.TimeZone.setDefault(TimeZone.getTimeZone("Europe/Tallinn"));
        folderStructurePattern = DirectoryStructureFileCreationPattern.builder().build();
    }

    @Test
    public void justCreatdShouldHaveEmptyString() {
        assertThat(folderStructurePattern.toString()).hasSize(36).contains("-");
    }

    @Test
    public void fillingYear() {
        folderStructurePattern.setYear(2018);
        assertThat(folderStructurePattern.toString()).startsWith("2018/");
    }

    @Test
    public void fillingYearAndMonth() {
        folderStructurePattern.setYear(2018).setMonth(12);
        assertThat(folderStructurePattern.toString()).startsWith("2018/12/");
    }

    @Test
    public void fillingYearAndMonthAndDay() {
        folderStructurePattern.setYear(2018).setMonth(12).setDay(31);
        assertThat(folderStructurePattern.toString()).startsWith("2018/12/31/");
    }

    @Test
    public void fillingYearAndMonthAndDayAndHour() {
        folderStructurePattern.setYear(2018).setMonth(12).setDay(31).setHour(23);
        assertThat(folderStructurePattern.toString()).startsWith("2018/12/31/23/");
    }

    @Test
    public void fillingYearAndMonthAndDayAndHourAndMinute() {
        folderStructurePattern.setYear(2018).setMonth(12).setDay(31).setHour(23).setMinute(59);
        assertThat(folderStructurePattern.toString()).startsWith("2018/12/31/23/59/");
    }

    @Test
    public void fillingYearAndMonthAndDayAndHourAndMinuteAndSecond() {
        folderStructurePattern.setYear(2018).setMonth(12).setDay(31).setHour(23).setMinute(59).setSecond(59);
        assertThat(folderStructurePattern.toString()).startsWith("2018/12/31/23/59/59/");
    }

    @Test
    public void fillingYearAndMonthAndDayAndHourAndMinuteAndSecondAndMilliSecond() {
        folderStructurePattern.setYear(2018).setMonth(12).setDay(31).setHour(23).setMinute(59).setSecond(59).setMilliSecond(1234);
        assertThat(folderStructurePattern.toString()).startsWith("2018/12/31/23/59/59/1234/");
    }

    @Test
    public void addDate() {
        folderStructurePattern.setDate(getDate(), DirectoryStructureDepth.MILLISECOND);
        assertThat(folderStructurePattern.toString()).startsWith("2017/12/31/8/18/10/252/");
    }

    @Test
    public void addFull() {
        folderStructurePattern = DirectoryStructureFileCreationPattern.builder().owner("owner").subOwner("subOwner").build();
        folderStructurePattern.setDate(getDate(), DirectoryStructureDepth.MILLISECOND);
        assertThat(folderStructurePattern.toString()).startsWith("owner/subOwner/2017/12/31/8/18/10/252/");
    }

    @Test
    public void addFull2() {
        folderStructurePattern = DirectoryStructureFileCreationPattern.builder().owner(null).subOwner("subOwner").build();
        folderStructurePattern.setDate(getDate(), DirectoryStructureDepth.MILLISECOND);
        assertThat(folderStructurePattern.toString()).startsWith("subOwner/2017/12/31/8/18/10/252/");
    }

    @Test
    public void addFull3() {
        folderStructurePattern = DirectoryStructureFileCreationPattern.builder().owner("owner").subOwner(null).build();
        folderStructurePattern.setDate(getDate(), DirectoryStructureDepth.MILLISECOND);
        assertThat(folderStructurePattern.toString()).startsWith("owner/2017/12/31/8/18/10/252/");
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
