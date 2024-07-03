package info.setmy.accouning.tsd.model;

import info.setmy.tsd.models.TsdVorm;
import info.setmy.tsd.models.VormType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "tsd_vorm")
public class TsdForm extends TsdVorm {

    public TsdForm() {
        this(VormType.TSD);
    }

    public TsdForm(final VormType vormType) {
        this.setVorm(vormType);
    }
}
