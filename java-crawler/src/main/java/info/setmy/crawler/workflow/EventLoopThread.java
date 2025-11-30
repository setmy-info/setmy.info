package info.setmy.crawler.workflow;

import lombok.Getter;

@Getter
public class EventLoopThread extends Thread {

    private EventLoop eventLoop = new EventLoop(this);

    public void registerService(ExecutableService s) {
        eventLoop.registerService(s);
    }

    @Override
    public void run() {
        eventLoop.run();
    }

    public void end() {
        eventLoop.finish();
    }
}
