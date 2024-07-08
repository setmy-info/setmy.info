package info.setmy.models.math.geometry;

import info.setmy.models.math.geometry.base.Point2DBase;
import info.setmy.models.math.geometry.base.ReferenceOfFrameBase;
import lombok.NonNull;

import java.util.List;

public class ReferenceOfFrame2DDouble extends ReferenceOfFrameBase<Point2DBase<Double>, Double> {

    public ReferenceOfFrame2DDouble(Point2DBase<Double> position, @NonNull List<ReferenceOfFrameBase<Point2DBase<Double>, Double>> subReferences) {
        super(position, subReferences);
    }

    public void add(@NonNull Point2DDouble shift) {
        getPosition().setX(getPosition().getX() + shift.getX());
        getPosition().setY(getPosition().getY() + shift.getY());
    }
}
