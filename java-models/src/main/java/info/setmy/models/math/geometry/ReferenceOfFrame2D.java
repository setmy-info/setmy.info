package info.setmy.models.math.geometry;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@Accessors(chain = true)
@AllArgsConstructor
@RequiredArgsConstructor
public class ReferenceOfFrame2D {

    @NonNull
    private final GeometryProperties geometryProperties;

    private Point2D position;

    private ReferenceOfFrame2D parent;

    @Builder.Default
    private List<ReferenceOfFrame2D> subReferenceOfFrame = new ArrayList<>();

    public boolean add(final ReferenceOfFrame2D referenceOfFrame2D) {
        referenceOfFrame2D.setParent(this);
        return subReferenceOfFrame.add(referenceOfFrame2D);
    }

    public void transform(final Point2D shift) {
        if (position != null) {
            position.setX(position.getX() + shift.getX());
            position.setY(position.getY() + shift.getY());
        }
    }

    public MapObject2D calculate(final MapObject2D mapObject2D) {
        // TODO
        return null;
    }

    public Point2D calculate(final Point2D coordinate) {
        double x = coordinate.getX();
        double y = coordinate.getY();
        ReferenceOfFrame2D current = this;
        while (current != null) {
            x += current.getPosition().getX();
            y += current.getPosition().getY();
            current = current.getParent();
        }
        return new Point2D(x, y, coordinate.getDateTime());
    }
}
