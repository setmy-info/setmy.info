package info.setmy.models.math.geometry.base;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.Delegate;

@Getter
@Setter
@Accessors(chain = false)
@RequiredArgsConstructor
public class Rectangle2D<N extends Number> {

    private Point2DBase<N> position;

    @Delegate
    private Boundaries2DBase<N> boundaries;
}
