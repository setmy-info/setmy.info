package info.setmy.models.math.geometry.base;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@RequiredArgsConstructor
public class RectangularCuboid<G extends Point3DBase<N>, B extends Boundaries3DBase<N>, N extends Number> extends Rectangle<G, B, N> {

}
