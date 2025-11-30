package info.setmy.crawler.workflow;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import static java.lang.System.currentTimeMillis;

@Getter
@Setter
@Log4j2
@RequiredArgsConstructor
public abstract class EventLoopBase {

    private static final long DEFAULT_LOOP_TIMEOUT = 1000;

    private long expectedPeriod = DEFAULT_LOOP_TIMEOUT;
    private long start;
    private long end;

    private volatile boolean running;

    private final Thread thread;

    public void run() {
        running = true;
        while (running && !thread.isInterrupted()) {
            before();
            execute();
            after();
        }
    }

    public void finish() {
        running = false;
        thread.interrupt();
    }

    private void before() {
        start = currentTimeMillis();
    }

    public abstract void execute();

    private void after() {
        end = currentTimeMillis();
        long elapsed = end - start;
        long remainingToSleep = expectedPeriod - elapsed;
        if (remainingToSleep > 0) {
            try {
                Thread.sleep(remainingToSleep);
            } catch (InterruptedException e) {
                running = false;
                Thread.currentThread().interrupt();
            }
        }
    }
}
