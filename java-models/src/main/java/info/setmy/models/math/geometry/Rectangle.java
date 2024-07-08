package info.setmy.models.math.geometry;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Builder(toBuilder = true)
@Accessors(chain = true)
@AllArgsConstructor
public class Rectangle {

    @NonNull
    private Point2D position;

    @NonNull
    private Boundaries2D boundaries;
}
