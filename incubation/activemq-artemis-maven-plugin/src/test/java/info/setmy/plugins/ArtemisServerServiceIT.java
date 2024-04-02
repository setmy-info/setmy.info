package info.setmy.plugins;


import lombok.extern.log4j.Log4j2;
import org.apache.activemq.artemis.api.core.ActiveMQBuffer;
import org.apache.activemq.artemis.api.core.ActiveMQException;
import org.apache.activemq.artemis.api.core.QueueConfiguration;
import org.apache.activemq.artemis.api.core.client.ActiveMQClient;
import org.apache.activemq.artemis.api.core.client.ClientConsumer;
import org.apache.activemq.artemis.api.core.client.ClientMessage;
import org.apache.activemq.artemis.api.core.client.ClientProducer;
import org.apache.activemq.artemis.api.core.client.ClientSession;
import org.apache.activemq.artemis.api.core.client.ClientSessionFactory;
import org.apache.activemq.artemis.api.core.client.ServerLocator;
import org.apache.activemq.artemis.core.server.embedded.EmbeddedActiveMQ;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Log4j2
public class ArtemisServerServiceIT {

    private ArtemisServerService service;

    @BeforeEach
    protected void before() {
        //service = ArtemisServerService.newInstance();
    }

    @AfterEach
    protected void after() {

    }

    @Test
    @Disabled
    public void testSomething() {
        service = ArtemisServerService.newInstance();
        service.start();

        final String queue = "example";
        final String messageString = "Hello World!";

        try {
            final ClientSession session = service.getClientSession();

            final ByteArrayInputStream inputStream = new ByteArrayInputStream(messageString.getBytes());
            session.createQueue(new QueueConfiguration(queue));
            final ClientProducer producer = session.createProducer(queue);
            final ClientMessage message = session.createMessage(true);
            message.setBodyInputStream(inputStream);
            producer.send(message);
            session.start();

            final ClientConsumer consumer = session.createConsumer(queue);
            final ClientMessage msgReceived = consumer.receive();
            assertThat(msgReceived.getStringBody()).isEqualTo("xxxx");

            session.close();
        } catch (ActiveMQException e) {
            throw new RuntimeException(e);
        }

        service.stop();
    }

    @Test
    public void xyz() throws Exception {
        final String queue = "example";
        final String messageString = "Hello World!";
        final byte[] messageBytes = messageString.getBytes(UTF_8);

        EmbeddedActiveMQ embedded = new EmbeddedActiveMQ();
        embedded.start();
        ServerLocator serverLocator = ActiveMQClient.createServerLocator("vm://0");
        ClientSessionFactory factory = serverLocator.createSessionFactory();
        ClientSession session = factory.createSession();

        session.createQueue(new QueueConfiguration(queue));

        ClientProducer producer = session.createProducer(queue);
        ClientMessage message = session.createMessage(true);
        ActiveMQBuffer bodyBuffer = message.getBodyBuffer();
        bodyBuffer.writeString(messageString);
        producer.send(message);

        session.start();
        ClientConsumer consumer = session.createConsumer(queue);
        ClientMessage msgReceived = consumer.receive();
        ActiveMQBuffer receivedBodyBuffer = msgReceived.getBodyBuffer();
        final String x = new String(receivedBodyBuffer.byteBuf().array());
        log.info("message = {}", x);
        session.close();
    }
}

