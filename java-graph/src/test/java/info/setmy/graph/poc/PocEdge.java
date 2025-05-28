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

    private WeightValue weightValue;

    @Override
    public String toString() {
        return getSource().toString() + " -> " + getTarget().toString() + " : " + weightValue.getWeight();
    }
}
