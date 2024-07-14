package info.setmy.models.math.geometry;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder(toBuilder = true)
@RequiredArgsConstructor
public class Rectangle3D<T extends Number> {
    private final Coordinate3D<T> position;
    private final Boundaries3D<T> boundaries;
}
