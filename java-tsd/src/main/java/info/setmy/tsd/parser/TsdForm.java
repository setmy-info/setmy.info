package info.setmy.tsd.parser;

import info.setmy.tsd.models.TsdVorm;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "tsd_vorm", namespace = "https://www.emta.ee/tsd_2023")
public class TsdForm extends TsdVorm {

}
