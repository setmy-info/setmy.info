package info.setmy.microservice;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static java.util.concurrent.CompletableFuture.allOf;
import static java.util.concurrent.CompletableFuture.completedFuture;

@Service
@RequiredArgsConstructor
public class ExampleService {

    private final Logger log = LogManager.getLogger(getClass());

    private final ExampleDao exampleDao;

    private final ExampleRepository exampleRepository;

    private final JDBCExampleDao jdbcExampleDao;

    private final JPAExampleDao jpaExampleDao;

    @Transactional
    public ExampleModel getExampleModel() {
        log.info("getExampleModel");
        //insertData();
        //final ExampleModel model = exampleDao.getExampleModel();
        final ExampleModel model = exampleRepository.findAll().get(0);
        final ExampleModel newModel = new ExampleModel().setId(model.getId()).setDateTime(model.getDateTime()).setText(model.getText());
        return newModel;
    }

    private void insertData() {
        ExampleModel model = new ExampleModel();
        model.setText("Hello World from JPA inserted data!");
        exampleRepository.save(model);

        model = new ExampleModel();
        model.setId(10L);// Not needed with autoincrement DB
        model.setText("Hello World from JDBC inserted data vol 2!");
        jdbcExampleDao.save(model);

        model = new ExampleModel();
        model.setText("Hello World from JPA inserted data vol 3!");
        jpaExampleDao.save(model);
    }

    public String getAsyncCallsResults() {
        final CompletableFuture<String> foo = getFoo();
        final CompletableFuture<String> bar = getBar();
        final CompletableFuture<String> more = getMore();
        allOf(foo, bar, more).join();
        try {
            final String result = foo.get() + ":" + bar.get() + ":" + more.get();
            log.info("Got results from Futures: '{}'", result);
            return result;
        } catch (InterruptedException | ExecutionException ex) {
            log.error("Error: ", ex);
            throw new RuntimeException(ex);
        }
    }

    @Async
    public CompletableFuture<String> getFoo() {
        sleep(1000L);
        return completedFuture("Foo");
    }

    @Async
    public CompletableFuture<String> getBar() {
        sleep(1200L);
        return completedFuture("Bar");
    }

    @Async
    public CompletableFuture<String> getMore() {
        sleep(500L);
        return completedFuture("More");
    }

    private void sleep(final long timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException ex) {
        }
    }
}
