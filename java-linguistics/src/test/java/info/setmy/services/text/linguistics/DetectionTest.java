package info.setmy.services.text.linguistics;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DetectionTest {

    Detection detection;

    @Before
    public void setUp() {
        detection = new Detection();
    }

    @Test
    public void pairingTest() {
        assertThat(detection.arePairedQuotes('«', '»'), is(equalTo(true)));
        assertThat(detection.arePairedQuotes('‘', '’'), is(equalTo(true)));
        assertThat(detection.arePairedQuotes('“', '”'), is(equalTo(true)));
        assertThat(detection.arePairedQuotes('„', '”'), is(equalTo(true)));
        assertThat(detection.arePairedQuotes('«', '’'), is(equalTo(false)));
        assertThat(detection.arePairedQuotes('«', ' '), is(equalTo(false)));
    }
}
