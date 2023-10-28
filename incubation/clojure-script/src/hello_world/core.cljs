(ns hello-world.core)

(defn logging [param]
  (js/console.log "ClojureScript function called with parameter: " param))

(println "Hello world!")

