package info.setmy.jwt.models;

import info.setmy.jwt.services.JWTService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import org.junit.jupiter.api.BeforeAll;

public class Data {

    protected static JWTService service;

    protected static JWTService rsaService;

    protected ExtendedJWTToken token;

    protected ExtendedJWTToken rsaToken;

    protected final static String SERVICE_NAME = "JWT_FOR_FRONTENDS_SERVICE";

    protected final static String RSA_SERVICE_NAME = "RSA_JWT_FOR_FRONTENDS_SERVICE";

    protected final static String ISSUER = "Hear And See Systems LLC";

    //Generated with : http://passwordsgenerator.net/
    protected final static String EXAMPLE_SECRET_KEY = "7jxFWY8BhuQtLCjYJjPv4bX8";

    protected final static String KEYS_FOLDER = "src/test/resources/rsa";

    protected final static String EXAMPLE_PRIVATE_KEY_FILE_NAME = KEYS_FOLDER + "/priv.key.der";

    protected final static String EXAMPLE_PUBLIC_KEY_FILE_NAME = KEYS_FOLDER + "/pub.key.pem";

    protected final static int EXAMPLE_SESSION_MINUTES = 15;

    protected final static String ALGORITHM_NAME = "HS256";

    protected final static String RSA_ALGORITHM_NAME = "RS256";

    public final static String UUID_STRING = "afad8212-27da-46cd-a90b-ce7c2502b953";

    protected final static Date NOW;

    protected final static String DATE_FORMAT_STRING = "dd.MM.yyyy hh:mm:ss";

    protected final static Date MOMENT;
    protected final static Date MOMENT_MINUS_15;
    protected final static Calendar CALENDAR_MOMENT_MINUS_15;
    protected final static Date MOMENT_MINUS_30;
    protected final static Calendar CALENDAR_MOMENT_MINUS_30;

    protected final static Calendar calendarNow1;
    protected final static Calendar calendarNow2;
    protected final static Calendar calendarNow3;
    protected final static Calendar calendarNow4;

    protected final static Date nowMinus20;
    protected final static Date nowMinus10;
    protected final static Date nowPlus10;
    protected final static Date nowPlus20;

    static {
        NOW = new Date();
        calendarNow1 = new GregorianCalendar();
        calendarNow2 = new GregorianCalendar();
        calendarNow3 = new GregorianCalendar();
        calendarNow4 = new GregorianCalendar();
        calendarNow1.setTime(NOW);
        calendarNow2.setTime(NOW);
        calendarNow3.setTime(NOW);
        calendarNow4.setTime(NOW);
        final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_STRING);
        sdf.setTimeZone(TimeZone.getTimeZone("Europe/Tallinn"));
        String dateInString = "28.12.2100 10:15:00";
        Date moment = null;
        try {
            moment = sdf.parse(dateInString);
        } catch (ParseException ex) {
        }
        MOMENT = moment;
        CALENDAR_MOMENT_MINUS_15 = new GregorianCalendar();
        CALENDAR_MOMENT_MINUS_15.setTime(MOMENT);
        CALENDAR_MOMENT_MINUS_15.add(Calendar.MINUTE, -15);
        MOMENT_MINUS_15 = CALENDAR_MOMENT_MINUS_15.getTime();

        CALENDAR_MOMENT_MINUS_30 = new GregorianCalendar();
        CALENDAR_MOMENT_MINUS_30.setTime(MOMENT);
        CALENDAR_MOMENT_MINUS_30.add(Calendar.MINUTE, -30);
        MOMENT_MINUS_30 = CALENDAR_MOMENT_MINUS_30.getTime();

        calendarNow1.add(Calendar.MINUTE, -20);
        nowMinus20 = calendarNow1.getTime();

        calendarNow2.add(Calendar.MINUTE, -10);
        nowMinus10 = calendarNow2.getTime();

        calendarNow3.add(Calendar.MINUTE, 10);
        nowPlus10 = calendarNow3.getTime();

        calendarNow4.add(Calendar.MINUTE, 20);
        nowPlus20 = calendarNow4.getTime();
    }

    static boolean isDone = false;

    @BeforeAll
    public static void beforeAll() {
        if (!isDone) {
            doBeforeAllOnlyOnce();
            isDone = true;
        }
    }

    public static void doBeforeAllOnlyOnce() {
        service = new JWTService(
                SERVICE_NAME,
                ISSUER,
                EXAMPLE_SECRET_KEY,
                ALGORITHM_NAME,
                EXAMPLE_SESSION_MINUTES);
        service.init();

        rsaService = new JWTService(
                RSA_SERVICE_NAME,
                ISSUER,
                EXAMPLE_PRIVATE_KEY_FILE_NAME,
                EXAMPLE_PUBLIC_KEY_FILE_NAME,
                RSA_ALGORITHM_NAME,
                EXAMPLE_SESSION_MINUTES);
        rsaService.init();

        System.out.println("==== Dates ===");
        System.out.println("Date -20: " + nowMinus20.toString());
        System.out.println("Date -10: " + nowMinus10.toString());
        System.out.println("Date now: " + NOW.toString());
        System.out.println("Date: 10: " + nowPlus10.toString());
        System.out.println("Date: 20: " + nowPlus20.toString());
        System.out.println("==============");
        System.out.println("Date: MOMENT_MINUS_30: " + MOMENT_MINUS_30.toString());
        System.out.println("Date: MOMENT_MINUS_15: " + MOMENT_MINUS_15.toString());
        System.out.println("Date: MOMENT:          " + MOMENT.toString());
        System.out.println("==============");
        isDone = true;
    }
}
