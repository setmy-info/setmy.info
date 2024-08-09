package info.setmy.postscript;

import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
public class HeaderDirective extends Comment {

    private final String comment;

    public HeaderDirective(final String name, final String comment) {
        super(name);
        this.comment = comment;
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        //appendCommandName(stringBuilder.append("%"));
        stringBuilder.append("%");
        stringBuilder.append(super.toString());
        stringBuilder.append(": ");
        stringBuilder.append(comment);
        //stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}
