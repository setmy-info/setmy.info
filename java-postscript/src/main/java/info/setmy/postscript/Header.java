package info.setmy.postscript;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Getter
@Builder(toBuilder = true)
@Accessors(chain = true)
@RequiredArgsConstructor
public class Header {

    private final static String HEADER = "%!PS-Adobe-3.0 EPSF-3.0";

    private final String creator;//%%Creator: usually program that generated the postscript
    private final String title;//%%Title: ame or usually file name
    private final LocalDateTime creationDate;//%%CreationDate: date when the file was created
    private final LocalDateTime documentData;//%%DocumentData: Clean7Bit
    //%%Origin: [eg: 0 0]
    //%%BoundingBox: 0 0 width height
    //%%LanguageLevel: 2
    //%%Pages: 1
    //%%Page: 1 1

    @Override
    public String toString() {
        return HEADER;
    }
}
