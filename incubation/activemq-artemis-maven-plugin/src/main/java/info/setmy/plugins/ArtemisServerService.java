package info.setmy.plugins;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.activemq.artemis.api.core.client.ActiveMQClient;
import org.apache.activemq.artemis.api.core.client.ClientSessionFactory;
import org.apache.activemq.artemis.api.core.client.ServerLocator;
import org.apache.activemq.artemis.core.config.Configuration;
import org.apache.activemq.artemis.core.config.impl.ConfigurationImpl;
import org.apache.activemq.artemis.core.server.embedded.EmbeddedActiveMQ;

import static java.util.Objects.isNull;

@Getter
@RequiredArgsConstructor
public class ArtemisServerService {

    private final EmbeddedActiveMQ server;
    private final Configuration configuration;

    private ServerLocator serverLocator;
    private ClientSessionFactory factory;

    private static ArtemisServerService artemisServerService;

    public static synchronized ArtemisServerService getInstance() {
        if (artemisServerService != null) {
            return artemisServerService;
        }
        try {
            final ConfigurationImpl conf = new ConfigurationImpl();
            conf.addAcceptorConfiguration("in-vm", "vm://0");
            conf.addAcceptorConfiguration("tcp", "tcp://127.0.0.1:61616");
            artemisServerService = new ArtemisServerService(new EmbeddedActiveMQ(), conf);
            artemisServerService.getServer().setConfiguration(conf);
            return artemisServerService;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArtemisServerService init() {
        if (!isNull(serverLocator) && !isNull(factory)) {
            return this;
        }
        try {
            //serverLocator = ActiveMQClient.createServerLocator("vm://0");
            //factory = serverLocator.createSessionFactory();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public ArtemisServerService start() {
        try {
            server.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return this;
    }


    public ArtemisServerService stop() {
        try {
            server.stop();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return this;
    }
}
