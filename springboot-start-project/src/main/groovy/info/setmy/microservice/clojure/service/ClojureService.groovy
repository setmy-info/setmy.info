package info.setmy.microservice.clojure.service

import clojure.java.api.Clojure
import clojure.lang.IFn
import groovy.util.logging.Slf4j
import org.springframework.stereotype.Service

@Slf4j
@Service
class ClojureService {

    void exec(final ClojureExec clojureExec) {
        log.info("Execute clojure")
        try {
            /*
            RT.loadResourceScript(clojureExec.scriptName);
            RT.var(clojureExec.ns, clojureExec.mainFunctionName).invoke(clojureExec.args);
            */
            Clojure.var("clojure.core", "require").invoke(Clojure.read(clojureExec.ns));
            IFn fun = Clojure.var(clojureExec.ns, clojureExec.mainFunctionName);
            fun.invoke(clojureExec.args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
