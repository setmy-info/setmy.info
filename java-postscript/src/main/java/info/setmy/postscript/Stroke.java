package info.setmy.postscript;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
@RequiredArgsConstructor
public class Stroke extends Command {

    private final static String COMMAND_NAME = "stroke";

    @Override
    public String toString() {
        return newStringBuilderWithCommand().toString();
    }

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }
}
