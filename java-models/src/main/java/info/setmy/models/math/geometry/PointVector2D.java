package info.setmy.models.math.geometry;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder(toBuilder = true)
@RequiredArgsConstructor
public class PointVector2D {

    private final Point2D coordinates;
    private final Vector2D headingVector;

    private final Object lock = new Object();

    private PointVector2D getPointVectorCopy() {
        Point2D coord;
        Vector2D vector;
        synchronized (lock) {
            coord = new Point2D(coordinates);
            vector = new Vector2D(headingVector);
        }
        return PointVector2D.builder()
            .coordinates(coord)
            .headingVector(vector)
            .build();
    }

    private void setPointVector(final Point2D coordinates, final Vector2D headingVector) {
        synchronized (lock) {
            this.coordinates.setX(coordinates.getX());
            this.coordinates.setY(coordinates.getY());
            this.coordinates.setDateTime(coordinates.getDateTime());

            this.headingVector.setX(headingVector.getX());
            this.headingVector.setY(headingVector.getY());
        }
    }
}
