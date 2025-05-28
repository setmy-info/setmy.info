package info.setmy.graph.poc;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MutableWeightPoCTest {

    DirectedGraphPoC graph;

    @BeforeEach
    void setUp() {
        graph = new DirectedGraphPoC();
    }

    @Test
    void poc_weightValueObject() {
        String nodeA = "A";
        String nodeB = "B";
        String nodeC = "C";
        Node a = new Node(nodeA);
        Node b = new Node(nodeB);
        Node c = new Node(nodeC);
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);

        WeightValue weightValueAB = new WeightValue("A->B", 0.9);
        WeightValue weightValueBC = new WeightValue("B->C", 0.8);
        WeightValue weightValueAC = new WeightValue("A->C", 0.5);

        Edge servAB = graph.addEdge(a, b);
        servAB.setWeightValue(weightValueAB);

        Edge servBC = graph.addEdge(b, c);
        servBC.setWeightValue(weightValueBC);

        Edge servAC = graph.addEdge(a, c);
        servAC.setWeightValue(weightValueAC);

        System.out.println("Graph start state: \n" + graph.edgeSet().toString().replace(", ", ",\n "));

        DijkstraShortestPath<Node, Edge> dijkstra = new DijkstraShortestPath<>(graph);
        System.out.println("\nShortest bath A->C in beginning: " + dijkstra.getPath(a, c));
        System.out.println("Road weight: " + dijkstra.getPathWeight(a, c));
        System.out.println("---------------------------------------------");

        System.out.println("🚀☇ EXTERNAL SATE CHANGE: Direct path A->C probability decreases significantly!");
        weightValueAB.setWeight(0.05);
        weightValueBC.setWeight(0.1);
        weightValueAC.setWeight(0.2);

        System.out.println("\nGraph new state: \n" + graph.edgeSet().toString().replace(", ", ",\n "));
        System.out.println("\nShortest path A->C after change: " + dijkstra.getPath(a, c));
        System.out.println("New path wight: " + dijkstra.getPathWeight(a, c));
    }
}
