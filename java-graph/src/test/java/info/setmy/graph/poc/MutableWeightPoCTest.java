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

        WeightCalculatorImpl calculatorAB = new WeightCalculatorImpl(0.9);
        WeightCalculatorImpl calculatorBC = new WeightCalculatorImpl(0.8);
        WeightCalculatorImpl calculatorAC = new WeightCalculatorImpl(0.5);

        PocEdge servAB = graph.addEdge(nodeA, nodeB);
        servAB.setWeightCalculator(calculatorAB);

        PocEdge servBC = graph.addEdge(nodeB, nodeC);
        servBC.setWeightCalculator(calculatorBC);

        PocEdge servAC = graph.addEdge(nodeA, nodeC);
        servAC.setWeightCalculator(calculatorAC);

        System.out.println("Graph start state: \n" + graph.edgeSet().toString().replace(", ", ",\n "));

        DijkstraShortestPath<String, PocEdge> dijkstra = new DijkstraShortestPath<>(graph);
        System.out.println("\nShortest bath A->C in beginning: " + dijkstra.getPath(nodeA, nodeC));
        System.out.println("Road weight: " + dijkstra.getPathWeight(nodeA, nodeC));
        System.out.println("---------------------------------------------");

        System.out.println("🚀☇ EXTERNAL SATE CHANGE: Direct path A->C probability decreases significantly!");
        calculatorAC.setProbability(0.05);

        System.out.println("\nGraph new state: \n" + graph.edgeSet().toString().replace(", ", ",\n "));
        System.out.println("\nShortest path A->C after change: " + dijkstra.getPath(nodeA, nodeC));
        System.out.println("New path wight: " + dijkstra.getPathWeight(nodeA, nodeC));
    }
}
