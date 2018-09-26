package info.setmy.microservice.universum.constants;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class MeasuringUnits {

    public static final long ASTRONOMICAL_UNITS_METERS = 149597870700L;
    public static final long LIGHT_YEAR_METERS = 9460730472580800L;
    public static final long PARSEC_METERS = 30856775814913673L;

    public static final long SOLAR_RADIUS_METERS = 696000000L;
    public static final long SOLAR_DIAMETER_METERS = SOLAR_RADIUS_METERS * 2L;

    public static final long EARTH_RADIUS_METERS = 6378140L;
    public static final long EARTH_DIAMETER_METERS = EARTH_RADIUS_METERS * 2L;

    public static final long SCALE = (EARTH_DIAMETER_METERS * 100L) / 2;

    private MeasuringUnits() {
    }
}
