package info.setmy.microservice.clojure.service


import clojure.lang.RT
import groovy.util.logging.Slf4j
import org.springframework.stereotype.Service

@Slf4j
@Service
class ClojureService {

    void exec(final ClojureExec clojureExec) {
        log.info("Execute clojure")
        try {
            RT.loadResourceScript(clojureExec.scriptName);
            RT.var(clojureExec.ns, clojureExec.mainFunctionName).invoke(clojureExec.args);
            //java.lang.IllegalStateException: Attempting to call unbound fn: #'info.setmy.clojure.core/-main
            /*
            IFn fun = Clojure.var(clojureExec.ns, clojureExec.mainFunctionName);
            fun.invoke(clojureExec.args);
            */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
