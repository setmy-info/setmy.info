package info.setmy.models.math.geometry;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder(toBuilder = true)
@RequiredArgsConstructor
public class Boundaries3D<T extends Number> {
    private final T width;//length
    private final T height;
    private final T depth;
}
