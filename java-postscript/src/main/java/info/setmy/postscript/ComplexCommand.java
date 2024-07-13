package info.setmy.postscript;

import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;

@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
@NoArgsConstructor
public abstract class ComplexCommand {

    protected final ArrayList<Command> commands = new ArrayList<>();

    protected abstract void doMake();

    public ArrayList<Command> make() {
        doMake();
        return commands;
    }
}
