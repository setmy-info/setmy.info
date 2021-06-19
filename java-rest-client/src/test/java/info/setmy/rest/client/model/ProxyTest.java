package info.setmy.rest.client.model;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class ProxyTest {

    Proxy proxy;

    @Test
    public void xxx() {
        proxy = new Proxy("Name", 1234);
        assertThat(proxy.toString()).isEqualTo("Name:1234");
    }
}
