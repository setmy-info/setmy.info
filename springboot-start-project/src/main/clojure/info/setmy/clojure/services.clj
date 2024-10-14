(ns info.setmy.clojure.services)

;;https://clojure.org/guides/learn/functions#_closures

(defn greet  [name] (str "Hello, " name))

(def barService info.setmy.clojure.BarService/barService)

;(defn getBar [name] (.. info.setmy.clojure.BarService/barService (getBar name)))
(defn getBar [name] (.. barService (getBar name)))
