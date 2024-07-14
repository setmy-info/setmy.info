package info.setmy.postscript.base;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import static java.util.Arrays.stream;

@Getter
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
@RequiredArgsConstructor
public abstract class CommandBase implements Command {

    public abstract String getCommandName();

    public void appendSpace(final StringBuilder stringBuilder) {
        stringBuilder.append(" ");
    }

    public void appendCommandName(final StringBuilder stringBuilder) {
        stringBuilder.append(getCommandName());
    }

    protected StringBuilder newStringBuilderWithCommand(final Object... values) {
        final StringBuilder stringBuilder = new StringBuilder();
        stream(values).forEach(value -> stringBuilder.append(value.toString()).append(" "));
        appendCommandName(stringBuilder);
        return stringBuilder;
    }
}
