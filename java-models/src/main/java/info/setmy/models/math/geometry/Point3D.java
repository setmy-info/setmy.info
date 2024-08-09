package info.setmy.models.math.geometry;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class Point3D extends Point2D {

    private double z;

    public Point3D(final double x, final double y, final double z, final LocalDateTime dateTime) {
        super(x, y, dateTime);
        this.z = z;
    }

    public Point3D(final Point3D coordinates) {
        super(coordinates);
        this.z = coordinates.getZ();
    }

    @Override
    public double getLength() {
        return Math.sqrt(getX() * getX() + getY() * getY() + getZ() * getZ());
    }
}
