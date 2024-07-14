package info.setmy.postscript;

import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
public class Header extends Comment {

    private final static String TEXT = "!PS-Adobe-3.0 EPSF-3.0";

    private final Comment creator;//%%Creator: usually program that generated the postscript
    private final Comment title;//%%Title: ame or usually file name
    private final Comment creationDate;//%%CreationDate: date when the file was created
    private final Comment documentData;//%%DocumentData: Clean7Bit
    private final Comment origin;//%%Origin: [eg: 0 0]
    // A4
    private final Comment boundingBox;//%%BoundingBox: 0 0 595 842
    private final Comment languageLevel;//%%LanguageLevel: 2
    private final Comment pages;//%%Pages: 1
    private final Comment page;//%%Page: 1 1

    @Override
    public String getCommandName() {
        return TEXT;
    }
}
