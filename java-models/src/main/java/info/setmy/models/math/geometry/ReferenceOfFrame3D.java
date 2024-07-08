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
public class ReferenceOfFrame3D {

    @NonNull
    private final GeometryProperties geometryProperties;

    private Point3D position;

    private ReferenceOfFrame3D parent;

    @Builder.Default
    private List<ReferenceOfFrame3D> subReferenceOfFrame = new ArrayList<>();

    public boolean add(ReferenceOfFrame3D referenceOfFrame3D) {
        referenceOfFrame3D.setParent(this);
        return subReferenceOfFrame.add(referenceOfFrame3D);
    }

    public void transform(final Point3D shift) {
        if (position != null) {
            position.setX(position.getX() + shift.getX());
            position.setY(position.getY() + shift.getY());
            position.setZ(position.getZ() + shift.getZ());
        }
    }

    public Point3D calculate(final Point3D coordinate) {
        double x = coordinate.getX();
        double y = coordinate.getY();
        double z = coordinate.getZ();
        ReferenceOfFrame3D current = this;
        while (current != null) {
            x += current.getPosition().getX();
            y += current.getPosition().getY();
            z += current.getPosition().getZ();
            current = current.getParent();
        }
        return new Point3D(x, y, z);
    }
}
