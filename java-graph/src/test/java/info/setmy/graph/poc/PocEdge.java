package info.setmy.graph.poc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jgrapht.graph.DefaultEdge;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PocEdge extends DefaultEdge {

    private WeightCalculator weightCalculator;

    @Override
    public String toString() {
        return getSource().toString() + " -> " + getTarget().toString() + " : " + weightCalculator.toString();
    }
}
