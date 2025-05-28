package info.setmy.graph.poc;

import org.jgrapht.graph.DefaultDirectedGraph;

public class MyGraphg extends DefaultDirectedGraph<String, PocEdge> {

    public MyGraphg() {
        super(PocEdge.class);
    }

    @Override
    public double getEdgeWeight(PocEdge serv) {
        return serv.getWeightValue().getWeight();
    }
}
