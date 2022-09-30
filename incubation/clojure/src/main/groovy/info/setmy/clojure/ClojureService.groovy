package info.setmy.clojure

import clojure.lang.RT
import groovy.util.logging.Slf4j
import org.springframework.stereotype.Service

@Slf4j
@Service
class ClojureService {

    private static final String MAIN_CLJ = "info/setmy/main.clj"

    private String[] args

    void executeClojure() {
        log.info("Execute clojure")
        try {
            RT.loadResourceScript(MAIN_CLJ);
            RT.var("info.setmy.main", "main").invoke(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
