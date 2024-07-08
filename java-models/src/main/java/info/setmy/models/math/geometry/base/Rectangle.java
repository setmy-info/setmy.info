package info.setmy.models.math.geometry.base;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@RequiredArgsConstructor
public class Rectangle<G extends Point2DBase<N>, B extends Boundaries2DBase<N>, N extends Number> {

    private G position;

    private B boundaries;
}
