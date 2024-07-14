package info.setmy.models.math.geometry;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder(toBuilder = true)
@RequiredArgsConstructor
public class Rectangle2D<T extends Number> {
    private final Coordinate2D<T> position;
    private final Boundaries2D<T> boundaries;
}
