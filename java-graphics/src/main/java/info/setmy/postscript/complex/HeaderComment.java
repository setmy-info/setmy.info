package info.setmy.postscript.complex;

import info.setmy.postscript.Comment;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
public class HeaderComment extends Comment {

    private final static String TEXT = "!PS-Adobe-3.0 EPSF-3.0";

    public HeaderComment() {
        super(TEXT);
    }
}
