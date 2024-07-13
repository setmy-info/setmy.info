package info.setmy.postscript;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
@RequiredArgsConstructor
public class Translate extends Command {

    private final static String COMMAND_NAME = "translate";

    private final double x;
    private final double y;

    @Override
    public String toString() {
        return newStringBuilderWithCommand(x, y).toString();
    }

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }
}
