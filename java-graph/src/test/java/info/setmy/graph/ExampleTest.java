package info.setmy.graph;

import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;

// https://jgrapht.org/guide/UserOverview#development-setup
public class ExampleTest {

    @Test
    public void example1() throws URISyntaxException {
        Graph<URI, DefaultEdge> g = new DefaultDirectedGraph<>(DefaultEdge.class);

        URI google = new URI("http://www.google.com");
        URI wikipedia = new URI("http://www.wikipedia.org");
        URI jgrapht = new URI("http://www.jgrapht.org");

        // add the vertices
        g.addVertex(google);
        g.addVertex(wikipedia);
        g.addVertex(jgrapht);

        // add edges to create linking structure
        g.addEdge(jgrapht, wikipedia);
        g.addEdge(google, jgrapht);
        g.addEdge(google, wikipedia);
        g.addEdge(wikipedia, google);

        URI start = g
            .vertexSet().stream().filter(uri -> uri.getHost().equals("www.jgrapht.org")).findAny()
            .get();

        assertThat(start).isEqualTo(new URI("http://www.jgrapht.org"));
    }

    @Test
    public void example2() {
        org.graphstream.graph.Graph graph = new SingleGraph("Tutorial 1");
        graph.addNode("A" );
        graph.addNode("B" );
        graph.addNode("C" );
        graph.addEdge("AB", "A", "B");
        graph.addEdge("BC", "B", "C");
        graph.addEdge("CA", "C", "A");
        //System.setProperty("org.graphstream.ui", "swing");
        //Viewer viewer = graph.display();
        //viewer.disableAutoLayout();
        //viewer.enableAutoLayout();
    }
}
