package info.setmy.models.math.geometry;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class Boundaries3D extends Boundaries2D {

    private double depth;//length

    public Boundaries3D(final double width, final double height, final double depth) {
        super(width, height);
        this.depth = depth;
    }
}
