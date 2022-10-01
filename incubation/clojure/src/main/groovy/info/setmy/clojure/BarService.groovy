package info.setmy.clojure


import groovy.util.logging.Slf4j

@Slf4j
@Singleton
class BarService {

    public static final BarService barService = new BarService()

    String getBar(final String baseText) {
        "This is BAR for " + baseText;
    }

    void getInjectionWorks() {
        log.info("Works!!")
    }

    static getFoo() {
        "Foo"
    }
}
