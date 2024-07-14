package info.setmy.postscript;

import info.setmy.postscript.base.CommandBase;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
@RequiredArgsConstructor
public class Arc extends CommandBase {

    private final static String COMMAND_NAME = "arc";

    private final double x1;
    private final double y1;
    private final double x2;
    private final double y2;
    private final double degrees;

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        appendSpace(stringBuilder.append(x1));
        appendSpace(stringBuilder.append(y1));
        appendSpace(stringBuilder.append(x2));
        appendSpace(stringBuilder.append(y2));
        appendSpace(stringBuilder.append(degrees));
        appendCommandName(stringBuilder);
        return stringBuilder.toString();
    }

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }
}
