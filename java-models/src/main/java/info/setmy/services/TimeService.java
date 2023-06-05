package info.setmy.services;

public class TimeService {

    public final static TimeService timeService = new TimeService();

    public int secondsToMillis(final int seconds) {
        return seconds * 1000;
    }
}
