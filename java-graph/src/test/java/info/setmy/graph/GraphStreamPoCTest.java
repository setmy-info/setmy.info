package info.setmy.graph;

import org.graphstream.algorithm.APSP;
import org.graphstream.algorithm.BellmanFord;
import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Path;
import org.graphstream.graph.implementations.SingleGraph;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// https://jgrapht.org/guide/UserOverview#development-setup
public class GraphStreamPoCTest {

    @Test
    @DisplayName("PoC Nodes and edges with related or linked data to nodes and edges")
    public void poc() {
        Graph graph = new SingleGraph("Tutorial 1");

        Node nodeA = graph.addNode("A");
        Node nodeB = graph.addNode("B");
        Node nodeC = graph.addNode("C");

        nodeA.setAttribute("id", 1L);
        nodeB.setAttribute("id", 2L);
        nodeC.setAttribute("id", 3L);

        Edge edgeAB = graph.addEdge("AB", "A", "B", true);
        Edge edgeBC = graph.addEdge("BC", "B", "C", true);
        Edge edgeCA = graph.addEdge("CA", "C", "A", true);

        edgeAB.setAttribute("id", 11L);
        edgeBC.setAttribute("id", 12L);
        edgeCA.setAttribute("id", 13L);

        edgeAB.setAttribute("weight", 5.0);
        edgeBC.setAttribute("weight", 7.0);
        edgeCA.setAttribute("weight", 14.0);

        assertThat(edgeAB.isDirected()).isTrue();
        assertThat(edgeAB.isLoop()).isFalse();
        assertThat(edgeAB.getSourceNode().getId()).isEqualTo("A");
        assertThat(edgeAB.getTargetNode().getId()).isEqualTo("B");
        assertThat(edgeAB.getAttribute("weight")).isEqualTo(5.0);

        APSP apsp = new APSP();
        apsp.init(graph);
        apsp.setDirected(true);
        apsp.setWeightAttributeName("weight");
        apsp.compute();
        Node begin = graph.getNode("A");
        APSP.APSPInfo info = (APSP.APSPInfo) begin.getAttribute(APSP.APSPInfo.ATTRIBUTE_NAME);
        System.out.println(info.getShortestPathTo("C"));

        BellmanFord bf = new BellmanFord("weight", "A");
        bf.init(graph);
        bf.compute();
        System.out.println(bf.getShortestPath(graph.getNode("C")));

        Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, "length");
        dijkstra.init(graph);
        dijkstra.setSource(graph.getNode("A"));
        dijkstra.compute();

        for (Node node : graph)
            System.out.printf("%s->%s:%10.2f%n", dijkstra.getSource(), node,
                dijkstra.getPathLength(node));

        for (Node node : dijkstra.getPathNodes(graph.getNode("B")))
            node.setAttribute("ui.style", "fill-color: blue;");

        for (Edge edge : dijkstra.getTreeEdges())
            edge.setAttribute("ui.style", "fill-color: red;");

        System.out.println(dijkstra.getPath(graph.getNode("B")));

        List<Node> list1 = new ArrayList<Node>();
        for (Node node : dijkstra.getPathNodes(graph.getNode("B")))
            list1.add(0, node);

        List<Node> list2 = dijkstra.getPath(graph.getNode("B")).getNodePath();

        dijkstra.clear();

        dijkstra = new Dijkstra(Dijkstra.Element.NODE, null, null);
        dijkstra.init(graph);
        dijkstra.setSource(graph.getNode("A"));
        dijkstra.compute();

        for (Node node : graph)
            System.out.printf("%s->%s:%10.2f%n", dijkstra.getSource(), node,
                dijkstra.getPathLength(node));

        Iterator<Path> pathIterator = dijkstra.getAllPaths(graph.getNode("C")).iterator();
        while (pathIterator.hasNext())
            System.out.println(pathIterator.next());
    }
}
