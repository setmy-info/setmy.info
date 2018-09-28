package info.setmy.microservice.universum.models;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class CelestialObject {

    private final String name;

    private final long distance;

    private final long diameter;

    public CelestialObject(final String name, final long distance, final long diameter) {
        this.name = name;
        this.distance = distance;
        this.diameter = diameter;
    }

    public String getName() {
        return name;
    }

    public long getDistance() {
        return distance;
    }

    public long getDiameter() {
        return diameter;
    }

    public CelestialObject scale(final long scale) {
        return new CelestialObject(name, ((distance * 100L) / scale), ((diameter * 1000L) / scale));
    }
}
