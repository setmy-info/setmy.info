package info.setmy.clojure

class BarService {

    public static final BarService barService = new BarService()

    String getBar(String name) {
        "Bar: " + name
    }

    static String getFoo() {
        "Foooo"
    }
}
