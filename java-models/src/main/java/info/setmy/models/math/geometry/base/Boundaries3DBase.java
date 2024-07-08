package info.setmy.models.math.geometry.base;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class Boundaries3DBase<N extends Number> extends Boundaries2DBase<N> {
    private N depth;//length
}
