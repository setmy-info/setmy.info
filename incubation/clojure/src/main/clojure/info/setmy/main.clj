(ns info.setmy.main
    (:use info.setmy.services))

(defn main [args]
    (println "Hello World from Clojure!")
    (println "System: " (.. System (getProperties) (get "os.name")))
    (println "Java: " (.. info.setmy.clojure.BarService (getFoo)))
    (println "Java Service: " (.. info.setmy.clojure.BarService/barService (getBar "Imre")))
    (println "Service function: " (getBar "Imre"))
    (println "Hello World from Clojure!")
    (println (greet "Imre"))
    (println "Java main called clojure function with args: "
             (apply str (interpose " " args))))

(defn default-main [args]
    (println "Hello World from Clojure!" (apply str (interpose " " args))))
