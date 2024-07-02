package info.setmy.tsd.parser;

import info.setmy.jaxb.JAXBService;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class TSDParsingIT {

    private final String XML_LOCATION = "./src/test/resources/tsd_2023/modified/xml_naide_01.01.2023.xml";
    private final String OUT_XML_LOCATION = "./target/xml_naide_01.01.2023.xml";
    private final String XSD_LOCATION = "./src/main/xsd/tsd_schema_01012023.xsd";

    JAXBService service;

    @BeforeEach
    public void setUp() {
        service = new JAXBService();
    }

    @Test
    public void xmlParsing() {
        final TsdForm tsdForm = service.parse(XML_LOCATION, XSD_LOCATION, TsdForm.class);
        service.render(OUT_XML_LOCATION, XSD_LOCATION, null, null, tsdForm);
        final TsdForm tsdForm2 = service.parse(OUT_XML_LOCATION, XSD_LOCATION, TsdForm.class);
        assertThat(tsdForm2).isNotNull();
        assertThat(tsdForm2.getC108Aasta()).isEqualTo(2023L);
    }
}
