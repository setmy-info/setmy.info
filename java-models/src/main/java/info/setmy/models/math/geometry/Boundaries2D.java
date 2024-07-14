package info.setmy.models.math.geometry;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder(toBuilder = true)
@RequiredArgsConstructor
public class Boundaries2D<T extends Number> {
    private final T width;
    private final T height;
}
