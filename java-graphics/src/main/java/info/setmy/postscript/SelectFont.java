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
public class SelectFont extends CommandBase {

    private final static String COMMAND_NAME = "selectfont";

    private final int size;

    @Override
    public String toString() {
        return newStringBuilderWithCommand(size).toString();
    }

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }
}
