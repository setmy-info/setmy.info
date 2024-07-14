package info.setmy.models.math.geometry;

import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@RequiredArgsConstructor
public class Coordinate2D<T extends Number> {
    private final T x;
    private final T y;
}
