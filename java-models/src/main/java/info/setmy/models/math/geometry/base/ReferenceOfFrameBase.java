package info.setmy.models.math.geometry.base;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = false)
@RequiredArgsConstructor
public class ReferenceOfFrameBase<T extends Point2DBase<N>, N extends Number> {

    private final T position;

    private ReferenceOfFrameBase parentReference;

    @NonNull
    private final List<ReferenceOfFrameBase> subReferences;
}
