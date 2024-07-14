package info.setmy.postscript.complex;

import info.setmy.postscript.LineTo;
import info.setmy.postscript.MoveTo;
import info.setmy.postscript.NewPath;
import info.setmy.postscript.Stroke;
import info.setmy.postscript.base.CommandCollection;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Builder(toBuilder = true)
@Accessors(chain = true)
@RequiredArgsConstructor
public class Line extends CommandCollection {

    private final float x1;
    private final float y1;
    private final float x2;
    private final float y2;

    @Override
    public Line doMake() {
        add(new NewPath());
        add(new MoveTo(x1, y1));
        add(new LineTo(x2, y2));
        add(new Stroke());
        return this;
    }
}
