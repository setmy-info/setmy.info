package info.setmy.microservice.universum.services;

import static info.setmy.microservice.universum.constants.MeasuringUnits.ASTRONOMICAL_UNITS_METERS;
import static info.setmy.microservice.universum.constants.MeasuringUnits.EARTH_DIAMETER_METERS;
import static info.setmy.microservice.universum.constants.MeasuringUnits.SOLAR_DIAMETER_METERS;
import info.setmy.microservice.universum.models.CelestialObject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Named;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Named("universumService")
public class UniversumService {

    public List<CelestialObject> getScaled(final long scale) {
        return getPlanetary().stream().map(item -> item.scale(scale)).collect(Collectors.toList());
    }

    private List<CelestialObject> getPlanetary() {
        final List<CelestialObject> list = new ArrayList<>();
        list.add(new CelestialObject("Sun", 0L, SOLAR_DIAMETER_METERS));// Not planet
        list.add(new CelestialObject("Mercury", 57909050000L, radius(2439700L)));
        list.add(new CelestialObject("Venus", 108208000000L, radius(6051800L)));
        list.add(new CelestialObject("Earth", ASTRONOMICAL_UNITS_METERS, EARTH_DIAMETER_METERS));
        list.add(new CelestialObject("Moon", 384399000L, radius(1738100)));
        list.add(new CelestialObject("Mars", 227939200000L, radius(3396200L)));
        list.add(new CelestialObject("Jupiter", 778570000000L, radius(71492000L)));
        list.add(new CelestialObject("Saturn", 1433530000000L, radius(60268000L)));
        list.add(new CelestialObject("Uranus", 2875040000000L, radius(25559000L)));
        list.add(new CelestialObject("Neptune", 4500000000000L, radius(24764000L)));
        list.add(new CelestialObject("Pluto", 5906380000000L, radius(1188300L)));
        return list;
    }

    long radius(final long radius) {
        return 2L * radius;
    }
}
