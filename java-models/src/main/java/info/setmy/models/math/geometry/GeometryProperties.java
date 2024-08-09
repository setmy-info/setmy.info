package info.setmy.models.math.geometry;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;

@Builder
@RequiredArgsConstructor
public class GeometryProperties extends HashMap<String, Object> {

    private final Long id;
    private final String name;
}
