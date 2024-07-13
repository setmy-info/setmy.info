package info.setmy.postscript;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
@RequiredArgsConstructor
public class ShowPage extends Command {

    private final static String COMMAND_NAME = "showpage";

    @Override
    public String toString() {
        return newStringBuilderWithCommand().toString();
    }

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }
}
