package info.setmy.models.math.geometry;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class Point3D extends Point2D {

    private double z;

    public Point3D(final double x, final double y, final double z) {
        super(x, y);
        this.z = z;
    }

    public double getLength() {
        return Math.sqrt(getX() * getX() + getY() * getY() + getZ() * getZ());
    }
}
