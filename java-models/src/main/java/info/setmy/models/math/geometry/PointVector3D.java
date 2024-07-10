package info.setmy.models.math.geometry;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder(toBuilder = true)
@RequiredArgsConstructor
public class PointVector3D {

    private final Point3D coordinates;
    private final Vector3D headingVector;

    private final Object lock = new Object();

    private PointVector3D getPointVectorCopy() {
        Point3D coord;
        Vector3D vector;
        synchronized (lock) {
            coord = coordinates.toBuilder().build();
            vector = headingVector.toBuilder().build();
        }
        return PointVector3D.builder()
            .coordinates(coord)
            .headingVector(vector)
            .build();
    }

    private void setPointVector(final Point3D coordinates, final Vector3D headingVector) {
        synchronized (lock) {
            this.coordinates.setX(coordinates.getX());
            this.coordinates.setY(coordinates.getY());
            this.coordinates.setZ(coordinates.getZ());
            this.coordinates.setDateTime(coordinates.getDateTime());

            this.headingVector.setX(headingVector.getX());
            this.headingVector.setY(headingVector.getY());
            this.headingVector.setZ(headingVector.getZ());
        }
    }
}
