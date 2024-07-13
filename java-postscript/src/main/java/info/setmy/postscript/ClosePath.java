package info.setmy.postscript;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
@RequiredArgsConstructor
public class ClosePath extends Command {

    private final static String COMMAND_NAME = "closepath";

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        appendCommandName(stringBuilder);
        return stringBuilder.toString();
    }

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }
}
