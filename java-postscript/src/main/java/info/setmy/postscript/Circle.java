package info.setmy.postscript;

import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
@RequiredArgsConstructor
public class Circle extends ComplexCommand {

    private final double x1;
    private final double y1;
    private final double x2;
    private final double y2;
    private final double degrees;

    @Override
    protected void doMake() {
        commands.add(new Arc(x1, y1, x2, y2, degrees));
        commands.add(new ClosePath());
        commands.add(new Stroke());
    }
}
