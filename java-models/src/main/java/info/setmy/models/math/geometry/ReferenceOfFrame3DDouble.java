package info.setmy.models.math.geometry;

import info.setmy.models.math.geometry.base.Point3DBase;
import info.setmy.models.math.geometry.base.ReferenceOfFrameBase;
import lombok.NonNull;

import java.util.List;

public class ReferenceOfFrame3DDouble extends ReferenceOfFrameBase<Point3DBase<Double>, Double> {

    public ReferenceOfFrame3DDouble(Point3DDouble position, @NonNull List<ReferenceOfFrameBase<Point3DBase<Double>, Double>> subReferences) {
        super(position, subReferences);
    }

    public void transform(@NonNull Point3DDouble shift) {
        getPosition().setX(getPosition().getX() + shift.getX());
        getPosition().setY(getPosition().getY() + shift.getY());
        getPosition().setZ(getPosition().getZ() + shift.getZ());
    }
}
