(ns info.setmy.main)

(defn main [args]
    (println "Hello World from Clojure!")
    (println "Java main called clojure function with args: "
             (apply str (interpose " " args))))
