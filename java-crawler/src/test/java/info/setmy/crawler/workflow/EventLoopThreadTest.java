package info.setmy.crawler.workflow;

import info.setmy.crawler.workflow.EventLoopThread;
import info.setmy.crawler.workflow.ExecutableService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

@Log4j2
public class EventLoopThreadTest {

    ExecutableService service = (context) -> log.info("executing EventLoopThreadTest: {}", context);

    @Test
    public void test() throws InterruptedException {
        EventLoopThread loopThread = new EventLoopThread();
        loopThread.registerService(service);
        loopThread.start();
        Thread.sleep(5000);
        loopThread.end();
        loopThread.join();
        log.info("Loop ended");
    }
}
