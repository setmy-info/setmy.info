package info.setmy.models.math.geometry.base;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class Point3DBase<N extends Number> extends Point2DBase<N> {
    private N z;
}
