package info.setmy.postscript.complex;

import info.setmy.postscript.Comment;
import info.setmy.postscript.HeaderDirective;
import info.setmy.postscript.base.CommandCollection;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@Getter
@Builder(toBuilder = true)
@Accessors(chain = true)
public class Header extends CommandCollection {

    private final HeaderDirective creator = new HeaderDirective("Creator", "setmy.info PS creator");//%%Creator: usually program that generated the postscript
    private final HeaderDirective title;//%%Title: ame or usually file name
    private final HeaderDirective creationDate;//%%CreationDate: date when the file was created
    private final HeaderDirective documentData;//%%DocumentData: Clean7Bit
    private final HeaderDirective origin;//%%Origin: [eg: 0 0]
    // A4
    private final HeaderDirective boundingBox;//%%BoundingBox: 0 0 595 842
    private final HeaderDirective languageLevel;//%%LanguageLevel: 2
    private final HeaderDirective pages;//%%Pages: 1
    private final HeaderDirective page;//%%Page: 1 1

    @Override
    public Header doMake() {
        add(new HeaderComment());
        asOptional(creator).ifPresent(comment -> add(comment));
        asOptional(title).ifPresent(comment -> add(comment));
        asOptional(creationDate).ifPresent(comment -> add(comment));
        asOptional(documentData).ifPresent(comment -> add(comment));
        asOptional(boundingBox).ifPresent(comment -> add(comment));
        asOptional(languageLevel).ifPresent(comment -> add(comment));
        asOptional(pages).ifPresent(comment -> add(comment));
        asOptional(page).ifPresent(comment -> add(comment));
        add(new Comment("%EndComments"));
        return this;
    }

    public Optional<Comment> asOptional(final Comment comment) {
        return ofNullable(comment);
    }
}
