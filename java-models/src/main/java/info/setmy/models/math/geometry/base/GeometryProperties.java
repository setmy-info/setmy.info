package info.setmy.models.math.geometry.base;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;

@Builder
@RequiredArgsConstructor
public class GeometryProperties extends HashMap<String, Object> {

    private final String name;
}
