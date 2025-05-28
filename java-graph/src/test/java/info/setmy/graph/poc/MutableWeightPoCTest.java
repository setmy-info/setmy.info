package info.setmy.graph.poc;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MutableWeightPoCTest {

    MyGraphg graph;

    @BeforeEach
    void setUp() {
        graph = new MyGraphg();
    }

    @Test
    void poc_weightValueObject() {
        String nodeA = "A";
        String nodeB = "B";
        String nodeC = "C";
        graph.addVertex(nodeA);
        graph.addVertex(nodeB);
        graph.addVertex(nodeC);

        WeightValue weightValueAB = new WeightValue(0.9);
        WeightValue weightValueBC = new WeightValue(0.8);
        WeightValue weightValueAC = new WeightValue(0.5);

        PocEdge servAB = graph.addEdge(nodeA, nodeB);
        servAB.setWeightValue(weightValueAB);

        PocEdge servBC = graph.addEdge(nodeB, nodeC);
        servBC.setWeightValue(weightValueBC);

        PocEdge servAC = graph.addEdge(nodeA, nodeC);
        servAC.setWeightValue(weightValueAC);

        System.out.println("Graph start state: \n" + graph.edgeSet().toString().replace(", ", ",\n "));

        DijkstraShortestPath<String, PocEdge> dijkstra = new DijkstraShortestPath<>(graph);
        System.out.println("\nShortest bath A->C in beginning: " + dijkstra.getPath(nodeA, nodeC));
        System.out.println("Road weight: " + dijkstra.getPathWeight(nodeA, nodeC));
        System.out.println("---------------------------------------------");

        System.out.println("🚀☇ EXTERNAL SATE CHANGE: Direct path A->C probability decreases significantly!");
        weightValueAB.setWeight(0.05);
        weightValueBC.setWeight(0.1);
        weightValueAC.setWeight(0.2);

        System.out.println("\nGraph new state: \n" + graph.edgeSet().toString().replace(", ", ",\n "));
        System.out.println("\nShortest path A->C after change: " + dijkstra.getPath(nodeA, nodeC));
        System.out.println("New path wight: " + dijkstra.getPathWeight(nodeA, nodeC));
    }
}
