package info.setmy.models.math.geometry.base;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = false)
@RequiredArgsConstructor
public class Rectangle2D<G extends Point2DBase<N>, B extends Boundaries2DBase<N>, N extends Number> {

    private G position;

    private B boundaries;
}
