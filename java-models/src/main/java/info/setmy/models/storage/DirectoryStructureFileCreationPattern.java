package info.setmy.models.storage;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.*;

/**
 * Class for directory creation rules.
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Builder(toBuilder = true)
@AllArgsConstructor
public class DirectoryStructureFileCreationPattern implements StorageFileCreationPattern {

    private final String system;
    private final String owner;
    private final String subOwner;
    private Integer year;
    private Integer month;
    private Integer day;
    private Integer hour;
    private Integer minute;
    private Integer second;
    private Integer milliSecond;
    private String name;//^[0-9a-zA-Z_\-. ]{1,50}$

    private static final List<DepthSetter> SETTERS;

    static {
        final ArrayList temp = new ArrayList();
        temp.add(new YearDepthSetter());
        temp.add(new MonthDepthSetter());
        temp.add(new DayDepthSetter());
        temp.add(new HourSetter());
        temp.add(new MinuteSetter());
        temp.add(new SecondSetter());
        temp.add(new MilliSecondSetter());
        SETTERS = Collections.unmodifiableList(temp);
    }

    public DirectoryStructureFileCreationPattern setDate(final Date date, final DirectoryStructureDepth depth) {
        final Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        for (DepthSetter setter : SETTERS) {
            if (setter.isDepth(depth)) {
                setter.setValue(calendar, this);
                return this;
            } else {
                setter.setValue(calendar, this);
            }
        }
        return this;
    }

    public DirectoryStructureFileCreationPattern setYear(final int year) {
        this.year = year;
        return this;
    }

    public DirectoryStructureFileCreationPattern setMonth(final int month) {
        if (between(month, 1, 12)) {
            this.month = month;
        }
        return this;
    }

    public DirectoryStructureFileCreationPattern setDay(final int day) {
        if (between(day, 1, 31)) {
            this.day = day;
        }
        return this;
    }

    public DirectoryStructureFileCreationPattern setHour(final int hour) {
        if (between(hour, 1, 24)) {
            this.hour = hour;
        }
        return this;
    }

    public DirectoryStructureFileCreationPattern setMinute(final int minute) {
        if (between(minute, 0, 59)) {
            this.minute = minute;
        }
        return this;
    }

    public DirectoryStructureFileCreationPattern setSecond(final int second) {
        if (between(second, 0, 59)) {
            this.second = second;
        }
        return this;
    }

    public DirectoryStructureFileCreationPattern setMilliSecond(final int milliSecond) {
        this.milliSecond = milliSecond;
        return this;
    }

    public DirectoryStructureFileCreationPattern setName(final String name) {
        this.name = name;
        return this;
    }

    private boolean between(final int number, final int from, final int to) {
        return (number <= to && number >= from);
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        addString(system, stringBuilder);
        addString(owner, stringBuilder);
        addString(subOwner, stringBuilder);
        SETTERS.forEach((setter) -> setter.setValue(this, stringBuilder));
        return stringBuilder.toString();
    }

    public static StringBuilder addString(final String string, final StringBuilder stringBuilder) {
        if (string != null) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("/");
            }
            stringBuilder.append(string);
        }
        return stringBuilder;
    }

    @Override
    public String getName() {
        return name;
    }

    interface DepthSetter {

        boolean isDepth(final DirectoryStructureDepth depth);

        void setValue(final Calendar calendar, final DirectoryStructureFileCreationPattern depth);

        void setValue(final DirectoryStructureFileCreationPattern depth, final StringBuilder stringBuilder);
    }

    static abstract class DepthBase {

        public void addNumber(final Integer number, final StringBuilder stringBuilder) {
            if (number != null) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append("/");
                }
                stringBuilder.append(number.toString());
            }
        }
    }

    static class YearDepthSetter extends DepthBase implements DepthSetter {

        @Override
        public boolean isDepth(final DirectoryStructureDepth depth) {
            return depth == DirectoryStructureDepth.YEAR;
        }

        @Override
        public void setValue(final Calendar calendar, final DirectoryStructureFileCreationPattern pattern) {
            pattern.setYear(calendar.get(Calendar.YEAR));
        }

        @Override
        public void setValue(DirectoryStructureFileCreationPattern depth, StringBuilder stringBuilder) {
            addNumber(depth.year, stringBuilder);
        }
    }

    static class MonthDepthSetter extends DepthBase implements DepthSetter {

        @Override
        public boolean isDepth(final DirectoryStructureDepth depth) {
            return depth == DirectoryStructureDepth.MONTH;
        }

        @Override
        public void setValue(final Calendar calendar, final DirectoryStructureFileCreationPattern pattern) {
            pattern.setMonth(calendar.get(Calendar.MONTH) + 1);
        }

        @Override
        public void setValue(DirectoryStructureFileCreationPattern depth, StringBuilder stringBuilder) {
            addNumber(depth.month, stringBuilder);
        }
    }

    static class DayDepthSetter extends DepthBase implements DepthSetter {

        @Override
        public boolean isDepth(final DirectoryStructureDepth depth) {
            return depth == DirectoryStructureDepth.DAY;
        }

        @Override
        public void setValue(final Calendar calendar, final DirectoryStructureFileCreationPattern pattern) {
            pattern.setDay(calendar.get(Calendar.DAY_OF_MONTH));
        }

        @Override
        public void setValue(DirectoryStructureFileCreationPattern depth, StringBuilder stringBuilder) {
            addNumber(depth.day, stringBuilder);
        }
    }

    static class HourSetter extends DepthBase implements DepthSetter {

        @Override
        public boolean isDepth(final DirectoryStructureDepth depth) {
            return depth == DirectoryStructureDepth.HOUR;
        }

        @Override
        public void setValue(final Calendar calendar, final DirectoryStructureFileCreationPattern pattern) {
            pattern.setHour(calendar.get(Calendar.HOUR));
        }

        @Override
        public void setValue(DirectoryStructureFileCreationPattern depth, StringBuilder stringBuilder) {
            addNumber(depth.hour, stringBuilder);
        }
    }

    static class MinuteSetter extends DepthBase implements DepthSetter {

        @Override
        public boolean isDepth(final DirectoryStructureDepth depth) {
            return depth == DirectoryStructureDepth.MINUTE;
        }

        @Override
        public void setValue(final Calendar calendar, final DirectoryStructureFileCreationPattern pattern) {
            pattern.setMinute(calendar.get(Calendar.MINUTE));
        }

        @Override
        public void setValue(DirectoryStructureFileCreationPattern depth, StringBuilder stringBuilder) {
            addNumber(depth.minute, stringBuilder);
        }
    }

    static class SecondSetter extends DepthBase implements DepthSetter {

        @Override
        public boolean isDepth(final DirectoryStructureDepth depth) {
            return depth == DirectoryStructureDepth.SECOND;
        }

        @Override
        public void setValue(final Calendar calendar, final DirectoryStructureFileCreationPattern pattern) {
            pattern.setSecond(calendar.get(Calendar.SECOND));
        }

        @Override
        public void setValue(DirectoryStructureFileCreationPattern depth, StringBuilder stringBuilder) {
            addNumber(depth.second, stringBuilder);
        }
    }

    static class MilliSecondSetter extends DepthBase implements DepthSetter {

        @Override
        public boolean isDepth(final DirectoryStructureDepth depth) {
            return depth == DirectoryStructureDepth.MILLISECOND;
        }

        @Override
        public void setValue(final Calendar calendar, final DirectoryStructureFileCreationPattern pattern) {
            pattern.setMilliSecond(calendar.get(Calendar.MILLISECOND));
        }

        @Override
        public void setValue(DirectoryStructureFileCreationPattern depth, StringBuilder stringBuilder) {
            addNumber(depth.milliSecond, stringBuilder);
        }
    }

    public enum DirectoryStructureDepth {
        YEAR,
        MONTH,
        DAY,
        HOUR,
        MINUTE,
        SECOND,
        MILLISECOND
    }
}
