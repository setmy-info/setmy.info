package info.setmy.postscript.complex;

import info.setmy.postscript.*;
import info.setmy.postscript.base.CommandCollection;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Builder(toBuilder = true)
@Accessors(chain = true)
@RequiredArgsConstructor
public class Rectangle extends CommandCollection {

    private final float x;
    private final float y;
    private final float width;
    private final float height;

    @Override
    public Rectangle doMake() {
        var x2 = x + width;
        var y2 = y + height;
        add(new NewPath());
        add(new MoveTo(x, y));
        add(new LineTo(x2, y));
        add(new LineTo(x2, y2));
        add(new LineTo(x, y2));
        add(new LineTo(x, y));
        add(new Stroke());
        return this;
    }
}
