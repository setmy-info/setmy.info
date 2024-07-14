package info.setmy.postscript.complex;

import info.setmy.postscript.base.Command;
import info.setmy.postscript.base.CommandCollection;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.stream;

@Getter
@Builder(toBuilder = true)
@Accessors(chain = true)
@RequiredArgsConstructor
public class Document {

    private final List<Command> commands = new ArrayList<>();

    public boolean add(final CommandCollection commandCollection) {
        return add((List<Command>) commandCollection.doMake());
    }

    public boolean add(final Command[] list) {
        return add(stream(list).toList());
    }

    public boolean add(final List<Command> list) {
        return commands.addAll(list);
    }

    public boolean add(final Command commandBase) {
        return commands.add(commandBase);
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        appendElements(stringBuilder);
        return stringBuilder.toString();
    }

    private void appendElements(final StringBuilder stringBuilder) {
        commands.forEach(command -> stringBuilder.append(command.toString()).append('\n'));
    }

    public void write(final File file) {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
