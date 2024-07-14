package info.setmy.models.math.geometry;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.synchronizedList;

@Getter
@Builder(toBuilder = true)
@RequiredArgsConstructor
public class MapObject2D {

    private final GeometryProperties geometryProperties;
    private final PointVector2D pointVector;
    private final List<PointVector2D> trajectory = synchronizedList(new ArrayList<>());
}
