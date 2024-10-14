package info.setmy.microservice.service

import clojure.lang.RT
import groovy.util.logging.Slf4j
import info.setmy.microservice.clojure.service.ClojureExec
import org.springframework.stereotype.Service

@Slf4j
@Service
class ClojureService {

    void exec(final ClojureExec clojureExec) {
        log.info("Execute clojure")
        try {
            RT.loadResourceScript(clojureExec.scriptName);
            RT.var(clojureExec.ns, clojureExec.mainFunctionName).invoke(clojureExec.args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
