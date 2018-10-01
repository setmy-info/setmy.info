package ee.pub.rhino;

/**
 * Called from JS.
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class Console {

    public void log(final String msg) {
        System.out.println(msg);
    }

    public Integer add(final Integer a, final Integer b) {
        return a + b;
    }
}
