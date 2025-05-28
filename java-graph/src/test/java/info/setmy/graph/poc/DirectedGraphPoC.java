package info.setmy.graph.poc;

import org.jgrapht.graph.DefaultDirectedGraph;

public class DirectedGraphPoC extends DefaultDirectedGraph<Node, Edge> {

    public DirectedGraphPoC() {
        super(Edge.class);
    }

    @Override
    public double getEdgeWeight(Edge serv) {
        return serv.getWeightValue().getWeight();
    }
}
