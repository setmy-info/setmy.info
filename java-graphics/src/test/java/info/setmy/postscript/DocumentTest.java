package info.setmy.postscript;

import info.setmy.postscript.complex.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DocumentTest {

    Document document;

    @BeforeEach
    public void setUp() {
        document = PSData.newDocument();
    }

    @Test
    public void test() {
        var psContent = document.toString();
        assertThat(psContent).isEqualTo(
            "%!PS-Adobe-3.0 EPSF-3.0\n" +
                "%%Creator: setmy.info PS creator\n" +
                "%%Title: Example PS from Java\n" +
                "%%CreationDate: 2024-07-14\n" +
                "%%DocumentData: Clean7Bit\n" +
                "%%EndComments\n" +
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
                "297.5 421.0 100.0 0.0 360.0 arc\n" +
                "closepath\n" +
                "stroke\n" +
                "% This is a comment\n" +
                "newpath\n" +
                "10.0 10.0 moveto\n" +
                "10.0 410.0 lineto\n" +
                "stroke\n" +
                "newpath\n" +
                "10.0 10.0 moveto\n" +
                "410.0 10.0 lineto\n" +
                "stroke\n" +
                "415.0 10.0 moveto\n" +
                "(x) show\n" +
                "10.0 415.0 moveto\n" +
                "(y) show\n" +
                "showpage\n" +
                "%%EOF\n"
        );
    }
}
