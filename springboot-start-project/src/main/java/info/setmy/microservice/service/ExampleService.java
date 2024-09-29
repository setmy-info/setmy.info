package info.setmy.microservice.service;

import info.setmy.microservice.dal.ExampleRepository;
import info.setmy.microservice.dal.HibernateExampleDao;
import info.setmy.microservice.dal.JDBCExampleDao;
import info.setmy.microservice.dal.JPAExampleDao;
import info.setmy.microservice.domain.ExampleModel;
import info.setmy.microservice.exception.ExampleRollbackException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.infinispan.manager.EmbeddedCacheManager;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static java.util.concurrent.CompletableFuture.allOf;
import static java.util.concurrent.CompletableFuture.completedFuture;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class ExampleService {

    private final ExampleRepository exampleRepository;

    private final JDBCExampleDao jdbcExampleDao;

    private final JPAExampleDao jpaExampleDao;

    private final HibernateExampleDao hibernateExampleDao;

    //private final EmbeddedCacheManager cacheManager;
    //private final RemoteCacheManager cacheManager;

    @Transactional
    public ExampleModel getExampleModel() {
        log.info("getExampleModel");
        //insertData();
        //final ExampleModel model = exampleDao.getExampleModel();
        //cacheManager.getCache("exampleCache").put("exampleKey", "Hello World from Cache!");
        //log.info("Cache: {}", cacheManager.getCache("exampleCache").get("exampleKey"));
        final ExampleModel model = exampleRepository.findAll().get(0);
        final ExampleModel newModel = new ExampleModel().setId(model.getId()).setDateTime(model.getDateTime()).setText(model.getText());
        return newModel;
    }

    @Transactional
    public ExampleModel getHibernateExampleModel() {
        log.info("getHibernateExampleModel");
        final ExampleModel model = hibernateExampleDao.findAll().get(0);
        return model;
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

    public ExampleModel doWithRollback(final ExampleModel exampleModel, final boolean doRollback) {
        exampleRepository.save(exampleModel);
        doCallThatThrowsForRollback(doRollback);
        return ExampleModel.builder()
            .text(exampleModel.getText())
            .build();
    }

    private void doCallThatThrowsForRollback(final boolean doRollback) {
        if (doRollback) {
            throw new ExampleRollbackException("Should not save");
        }
    }
}
