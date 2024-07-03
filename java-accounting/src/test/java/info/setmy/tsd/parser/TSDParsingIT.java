package info.setmy.tsd.parser;

import info.setmy.accouning.tsd.model.TsdForm;
import info.setmy.jaxb.JAXBService;
import info.setmy.tsd.models.LaadimisViisType;
import info.setmy.tsd.models.VormType;
import java.math.BigDecimal;
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
    private final String OUT2_XML_LOCATION = "./target/xml_naide-2_01.01.2023.xml";
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

    @Test
    public void xmlParsing2() {
        final TsdForm tsdForm = new TsdForm();
        tsdForm.setC108Aasta(2024L);
        tsdForm.setC109Kuu(8L);
        tsdForm.setC110Tm(new BigDecimal("0.0"));
        tsdForm.setC114TmEj(new BigDecimal("0.0"));
        tsdForm.setC115Sm(new BigDecimal("0.0"));
        tsdForm.setC116Tk(new BigDecimal("0.0"));
        tsdForm.setC117Kp(new BigDecimal("0.0"));
        tsdForm.setC118KohustKokku(new BigDecimal("0.0"));
        tsdForm.setC119TagastKokku(new BigDecimal("0.0"));
        tsdForm.setLaadimisViis(LaadimisViisType.P);
        tsdForm.setRegKood("1122334455");
        service.render(OUT2_XML_LOCATION, XSD_LOCATION, null, null, tsdForm);
        final TsdForm tsdForm2 = service.parse(OUT2_XML_LOCATION, XSD_LOCATION, TsdForm.class);
        assertThat(tsdForm2).isNotNull();
        assertThat(tsdForm2.getC108Aasta()).isEqualTo(2024L);
    }
}
