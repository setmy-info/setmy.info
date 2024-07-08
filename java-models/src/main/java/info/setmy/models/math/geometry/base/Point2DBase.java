package info.setmy.models.math.geometry.base;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class Point2DBase<N extends Number> {
    private N x;
    private N y;
}
