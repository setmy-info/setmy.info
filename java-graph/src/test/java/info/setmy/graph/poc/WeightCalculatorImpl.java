package info.setmy.graph.poc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static java.lang.Math.max;
import static java.lang.String.format;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeightCalculatorImpl implements WeightCalculator {

    private double probability;

    @Override
    public double calcWight() {
        return 1.0 / probability;
    }

    public void setProbability(double probability) {
        this.probability = max(0.0001, probability);
    }

    @Override
    public String toString() {
        return format("P(%.2f) -> Weight(%.2f)", probability, calcWight());
    }
}
