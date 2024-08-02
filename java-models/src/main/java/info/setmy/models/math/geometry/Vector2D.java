package info.setmy.models.math.geometry;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
@AllArgsConstructor
public class Vector2D {

    private double x;
    private double y;

    public Vector2D(final Vector2D vector) {
        this.x = vector.getX();
        this.y = vector.getY();
    }

    public double getLength() {
        return Math.sqrt(x * x + y * y);
    }
}
