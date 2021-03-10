package info.setmy.services.text.linguistics;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;

public class DetectionTest {

    Detection detection;

    @Before
    public void setUp() {
        detection = new Detection();
    }

    @Test
    public void pairingTest() {
        assertThat(detection.arePairedQuotes('«', '»')).isEqualTo(true);
        assertThat(detection.arePairedQuotes('‘', '’')).isEqualTo(true);
        assertThat(detection.arePairedQuotes('“', '”')).isEqualTo(true);
        assertThat(detection.arePairedQuotes('„', '”')).isEqualTo(true);
        assertThat(detection.arePairedQuotes('«', '’')).isEqualTo(false);
        assertThat(detection.arePairedQuotes('«', ' ')).isEqualTo(false);
    }
}
