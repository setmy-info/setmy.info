package info.setmy.models.math.geometry.base;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = false)
public class Boundaries2DBase<N extends Number> {
    private N width;
    private N height;
}
