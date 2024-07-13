package info.setmy.postscript;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DocumentTest {

    Document document;

    @BeforeEach
    public void setUp() {
        document = Document.builder()
            .header(Header.builder().build())
            .build();
    }

    @Test
    public void test() {
        document.add(new FontName("Courier"));
        document.add(new SelectFont(20));
        document.add(new MoveTo(72, 500));
        document.add(new Show("Hello world!"));
        document.add(new Rectangle(144, 144, 144, 144));
        document.add(new Circle(4, 5, 3, 0, 360));
        document.add(new ShowPage());
        var psContent = document.toString();
        assertThat(psContent).isEqualTo(
            "%!PS-Adobe-3.0 EPSF-3.0\n" +
                "/Courier\n" +
                "20 selectfont\n" +
                "72.0 500.0 moveto\n" +
                "(Hello world!) show\n" +
                "newpath\n" +
                "144.0 144.0 moveto\n" +
                "288.0 144.0 lineto\n" +
                "288.0 288.0 lineto\n" +
                "144.0 288.0 lineto\n" +
                "144.0 144.0 lineto\n" +
                "stroke\n" +
                "4.0 5.0 3.0 0.0 360.0 arc\n" +
                "closepath\n" +
                "stroke\n" +
                "showpage\n"
        );
    }
}
