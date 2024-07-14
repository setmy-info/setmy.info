package info.setmy.postscript;

import info.setmy.postscript.complex.Circle;
import info.setmy.postscript.complex.Document;
import info.setmy.postscript.complex.Header;
import info.setmy.postscript.complex.Rectangle;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PSData {

    public Document newDocument() {
        final Document document = new Document();
        document.add(
            Header.builder()
                .title(new HeaderDirective("Title", "Example PS from Java"))
                .creationDate(new HeaderDirective("CreationDate", "2024-07-14"))
                .documentData(new HeaderDirective("DocumentData", "Clean7Bit"))
                .build()
        );
        document.add(new FontName("Courier"));
        document.add(new SelectFont(20));
        document.add(new MoveTo(72, 500));
        document.add(new Show("Hello world!"));
        document.add(new Rectangle(144, 144, 144, 144));
        document.add(new Circle(297.5D, 421, 100, 0, 360));
        document.add(new Comment(" This is a comment"));
        document.add(new ShowPage());
        document.add(new Comment("%EOF"));
        return document;
    }
}
