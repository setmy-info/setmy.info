package info.setmy.models.math.geometry;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Builder(toBuilder = true)
@Accessors(chain = true)
@AllArgsConstructor
public class RectangularCuboid {

    @NonNull
    private Point3D position;

    @NonNull
    private Boundaries3D boundaries;
}
