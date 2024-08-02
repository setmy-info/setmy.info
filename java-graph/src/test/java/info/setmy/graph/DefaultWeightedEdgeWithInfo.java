package info.setmy.graph;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.jgrapht.graph.DefaultWeightedEdge;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DefaultWeightedEdgeWithInfo extends DefaultWeightedEdge {

    private String additionalInfo;
}
