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
public class SetGray extends CommandBase {

    private final static String COMMAND_NAME = "setrgbcolor";

    private final double gray;

    @Override
    public String toString() {
        return newStringBuilderWithCommand(gray).toString();
    }

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }
}
