package info.setmy.models.math.geometry;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
public class Point2D extends Vector2D {

    private LocalDateTime dateTime;

    public Point2D(double x, double y, final LocalDateTime dateTime) {
        super(x, y);
        this.dateTime = dateTime;
    }
}
