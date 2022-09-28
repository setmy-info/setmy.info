(ns info.setmy.testing
    (:use clojure.test))

(deftest hello-test
    (println "Hello World testing 1"))

(deftest add-1-to-1
    (is (= 2 (+ 1 1))))
