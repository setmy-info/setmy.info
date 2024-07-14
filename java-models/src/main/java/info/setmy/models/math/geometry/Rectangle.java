package info.setmy.models.math.geometry;

import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@Builder(toBuilder = true)
@Accessors(chain = true)
@AllArgsConstructor
public class Rectangle {

    @NonNull
    private Point2D position;
}
