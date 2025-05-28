package info.setmy.graph.poc;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WeightValue extends NamedBase {

    private double weight;

    public WeightValue(String name) {
        super(name);
    }

    public WeightValue(String name, double weight) {
        super(name);
        this.weight = weight;
    }
}
