package info.setmy.models.math.geometry.base;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@RequiredArgsConstructor
public class ReferenceOfFrameBase<G extends Point2DBase<N>, N extends Number> {

    @NonNull
    private final G position;

    private ReferenceOfFrameBase<G, N> parentReference;

    private GeometryProperties geometryProperties;

    @NonNull
    private final List<ReferenceOfFrameBase<G, N>> subReferences;

    public void add(@NonNull ReferenceOfFrameBase<G, N> reference) {
        reference.setParentReference(this);
        subReferences.add(reference);
    }
}
