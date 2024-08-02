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
public class Show extends CommandBase {

    private final static String COMMAND_NAME = "show";

    private final String text;

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        appendCommandName(
            stringBuilder
                .append("(").
                append(text)
                .append(")")
                .append(" ")
        );
        return stringBuilder.toString();
    }

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }
}
