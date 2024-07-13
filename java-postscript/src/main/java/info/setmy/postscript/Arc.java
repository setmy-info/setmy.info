package info.setmy.postscript;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
@RequiredArgsConstructor
public class Arc extends Command {

    private final static String COMMAND_NAME = "arc";

    private final double x1;
    private final double y1;
    private final double x2;
    private final double y2;
    private final double degrees;

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        appendCommandName(
            stringBuilder
                .append(x1).append(" ")
                .append(y1).append(" ")
                .append(x2).append(" ")
                .append(y2).append(" ")
                .append(degrees).append(" ")
        );
        return stringBuilder.toString();
    }

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }
}
