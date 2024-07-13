package info.setmy.postscript;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.stream;

@Getter
@Builder(toBuilder = true)
@Accessors(chain = true)
@RequiredArgsConstructor
public class Document {

    public final Header header;

    private final List<Command> commands = new ArrayList<>();

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        appendHeader(stringBuilder);
        appendElements(stringBuilder);
        return stringBuilder.toString();
    }

    public boolean add(final ComplexCommand complexCommand) {
        return add(complexCommand.make());
    }

    public boolean add(final Command[] list) {
        return add(stream(list).toList());
    }

    public boolean add(final List<Command> list) {
        return commands.addAll(list);
    }

    public boolean add(final Command command) {
        return commands.add(command);
    }

    private void appendHeader(final StringBuilder stringBuilder) {
        stringBuilder.append(header).append('\n');
    }

    private void appendElements(final StringBuilder stringBuilder) {
        commands.forEach(command -> stringBuilder.append(command.toString()).append('\n'));
    }
}
