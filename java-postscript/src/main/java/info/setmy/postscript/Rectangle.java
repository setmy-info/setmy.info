package info.setmy.postscript;

import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
@RequiredArgsConstructor
public class Rectangle extends ComplexCommand {

    private final float x;
    private final float y;
    private final float width;
    private final float height;

    @Override
    protected void doMake() {
        var x2 = x + width;
        var y2 = y + height;
        commands.add(new NewPath());
        commands.add(new MoveTo(x, y));
        commands.add(new LineTo(x2, y));
        commands.add(new LineTo(x2, y2));
        commands.add(new LineTo(x, y2));
        commands.add(new LineTo(x, y));
        commands.add(new Stroke());
    }
}
