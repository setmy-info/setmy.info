(ns foo-bar-lib-probe.core
    (:gen-class))

(defn foo [a b]
    (+ a b))

(defn -main
    "Leiningen clojars project example"
    [& args]
    (let [sum (foo 5 7)]
        (println (str "Sum: " sum))
        (println "Hello, World!")))
