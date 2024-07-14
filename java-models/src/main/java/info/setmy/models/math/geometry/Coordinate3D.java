package info.setmy.models.math.geometry;

import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@AllArgsConstructor
public class Coordinate3D<T extends Number>  {
    //private final T x;
    //private final T y;
    private final T z;
}
