package info.setmy.graph;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.BellmanFordShortestPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DirectedWeightedMultigraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.DepthFirstIterator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;

// https://jgrapht.org/guide/UserOverview#development-setup
public class JgraphtPoCTest {

    @Test
    @DisplayName("PoC Nodes and edges with related or linked data to nodes and edges")
    public void poc() throws URISyntaxException {

        // https://jgrapht.org/guide/UserOverview#hello-jgrapht
        Graph<URI, DefaultWeightedEdgeWithInfo> graph = new DirectedWeightedMultigraph<>(DefaultWeightedEdgeWithInfo.class);

        // Node/vertices/points data
        URI google = new URI("http://www.google.com");
        URI wikipedia = new URI("http://www.wikipedia.org");
        URI jgrapht = new URI("http://www.jgrapht.org");

        // add the node/vertices/points
        graph.addVertex(google);
        graph.addVertex(wikipedia);
        graph.addVertex(jgrapht);

        // add edges/arcs/links/lines to create linking structure
        DefaultWeightedEdgeWithInfo jgraphtWikipedia = graph.addEdge(jgrapht, wikipedia);
        jgraphtWikipedia.setAdditionalInfo("jgrapht -> wikipedia");
        DefaultWeightedEdgeWithInfo googleJgrapht = graph.addEdge(google, jgrapht);
        googleJgrapht.setAdditionalInfo("google -> jgrapht");
        DefaultWeightedEdgeWithInfo googleWikipedia = graph.addEdge(google, wikipedia);
        googleWikipedia.setAdditionalInfo("google -> wikipedia");
        DefaultWeightedEdgeWithInfo wikipediaGoogle = graph.addEdge(wikipedia, google);
        wikipediaGoogle.setAdditionalInfo("wikipedia -> google");

        graph.setEdgeWeight(jgraphtWikipedia, 1.1D);
        graph.setEdgeWeight(googleJgrapht, 2.4D);
        graph.setEdgeWeight(googleWikipedia, 3.4D);
        graph.setEdgeWeight(wikipediaGoogle, 4.4D);

        URI start = graph
            .vertexSet().stream()
            .filter(uri -> uri.getHost().equals("www.jgrapht.org"))
            .findAny()
            .get();

        assertThat(start).isEqualTo(new URI("http://www.jgrapht.org"));

        // DFS (depth-first) algorithm
        Iterator<URI> iterator = new DepthFirstIterator<>(graph, start);
        while (iterator.hasNext()) {
            URI uri = iterator.next();
            System.out.println("DFS: " + uri);
        }

        // BFS (breadth-first) algorithm
        BreadthFirstIterator<URI, DefaultWeightedEdgeWithInfo> bfsIterator = new BreadthFirstIterator<>(graph, google);
        while (bfsIterator.hasNext()) {
            URI vertex = bfsIterator.next();
            System.out.println("BFS: " + vertex);
        }

        // Dijkstra algorithm
        DijkstraShortestPath<URI, DefaultWeightedEdgeWithInfo> dijkstra = new DijkstraShortestPath<>(graph);
        double shortestPath = dijkstra.getPathWeight(google, jgrapht);
        GraphPath<URI, DefaultWeightedEdgeWithInfo> path = dijkstra.getPath(google, jgrapht);
        System.out.println("Shortest path weight: " + shortestPath);
        System.out.println("Shortest weighted path: " + path);

        // Bellman-Fordi algorithm
        ShortestPathAlgorithm<URI, DefaultWeightedEdgeWithInfo> bellmanFord = new BellmanFordShortestPath<>(graph);
        double shortestPathWeight = bellmanFord.getPathWeight(google, jgrapht);
        System.out.println("Shortest path weight: " + shortestPathWeight);
    }
}
