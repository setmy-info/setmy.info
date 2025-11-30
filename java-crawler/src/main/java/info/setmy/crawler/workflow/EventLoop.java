package info.setmy.crawler.workflow;

import lombok.Getter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Getter
public class EventLoop extends EventLoopBase {

    private final List<ExecutableService> services = new CopyOnWriteArrayList<>();
    private final FLowContext FLowContext = new FLowContext();

    public EventLoop(final Thread thread) {
        super(thread);
    }

    @Override
    public void execute() {
        services.forEach(service -> service.execute(FLowContext));
    }

    public void registerService(ExecutableService s) {
        services.add(s);
    }

    public void unregisterService(ExecutableService s) {
        services.remove(s);
    }

    public void clearServices() {
        services.clear();
    }
}
