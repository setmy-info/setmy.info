package info.setmy.postscript.complex;

import info.setmy.postscript.Arc;
import info.setmy.postscript.ClosePath;
import info.setmy.postscript.base.CommandCollection;
import info.setmy.postscript.Stroke;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Builder(toBuilder = true)
@Accessors(chain = true)
@RequiredArgsConstructor
public class Circle extends CommandCollection {

    private final double x1;
    private final double y1;
    private final double radius;
    private final double beginDegrees;
    private final double endDegrees;

    @Override
    public Circle doMake() {
        add(new Arc(x1, y1, radius, beginDegrees, endDegrees));
        add(new ClosePath());
        add(new Stroke());
        return this;
    }
}
