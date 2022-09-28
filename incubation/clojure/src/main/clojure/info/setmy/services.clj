(ns info.setmy.services)

;;https://clojure.org/guides/learn/functions#_closures

(defn greet  [name] (str "Hello, " name))

(def barService info.setmy.BarService/barService)

;(defn getBar [name] (.. info.setmy.BarService/barService (getBar name)))
(defn getBar [name] (.. barService (getBar name)))
