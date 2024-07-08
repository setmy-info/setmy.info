package info.setmy.models.math.geometry;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder(toBuilder = true)
@RequiredArgsConstructor
public class MapObject2D {

    private final GeometryProperties geometryProperties;
    private final Point2D coordinates;
    private final Point2D headingVector;
}
