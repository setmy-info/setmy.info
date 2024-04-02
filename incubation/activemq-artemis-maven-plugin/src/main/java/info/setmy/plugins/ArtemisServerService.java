package info.setmy.plugins;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.activemq.artemis.api.core.client.ActiveMQClient;
import org.apache.activemq.artemis.api.core.client.ClientSession;
import org.apache.activemq.artemis.api.core.client.ClientSessionFactory;
import org.apache.activemq.artemis.api.core.client.ServerLocator;
import org.apache.activemq.artemis.core.config.Configuration;
import org.apache.activemq.artemis.core.server.embedded.EmbeddedActiveMQ;

@Setter
@Getter
@RequiredArgsConstructor
public class ArtemisServerService {

    private EmbeddedActiveMQ embeddedActiveMQ;
    private Configuration configuration;
    private ServerLocator serverLocator;
    private ClientSessionFactory factory;

    public static synchronized ArtemisServerService newInstance() {
        try {
            final ArtemisServerService artemisServerService = new ArtemisServerService();
            final EmbeddedActiveMQ embeddedActiveMQ = new EmbeddedActiveMQ();
            artemisServerService.setEmbeddedActiveMQ(embeddedActiveMQ);
            /*
            final ConfigurationImpl config = new ConfigurationImpl();
            config.addAcceptorConfiguration("in-vm", "vm://0");
            config.addAcceptorConfiguration("tcp", "tcp://127.0.0.1:61616");
            embeddedActiveMQ.setConfiguration(config);
            */
            return artemisServerService;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArtemisServerService start() {
        try {
            embeddedActiveMQ.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return this;
    }


    public ArtemisServerService stop() {
        try {
            embeddedActiveMQ.stop();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public ClientSession getClientSession() {
        try {
            final ServerLocator serverLocator = ActiveMQClient.createServerLocator("vm://0");
            final ClientSessionFactory factory = serverLocator.createSessionFactory();
            final ClientSession session = factory.createSession();
            return session;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
