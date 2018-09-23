package info.setmy.rest.client.model;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class Proxy {

    private final String hostName;

    private final int port;

    public Proxy(final String hostName, final int port) {
        this.hostName = hostName == null ? "" : hostName;
        this.port = port;
    }

    @Override
    public String toString() {
        final StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(hostName);
        stringBuffer.append(":");
        stringBuffer.append(port);
        return stringBuffer.toString();
    }
}
