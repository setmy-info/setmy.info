package info.setmy.postscript.base;

import lombok.experimental.Accessors;

import java.util.ArrayList;

@Accessors(chain = true)
public abstract class CommandCollection extends ArrayList<Command> {

    public abstract CommandCollection doMake();
}
