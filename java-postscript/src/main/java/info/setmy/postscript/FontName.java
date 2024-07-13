package info.setmy.postscript;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
@RequiredArgsConstructor
public class FontName extends Command {

    private final String fontName;

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        appendCommandName(stringBuilder.append("/"));
        return stringBuilder.toString();
    }

    @Override
    public String getCommandName() {
        return fontName;
    }
}
