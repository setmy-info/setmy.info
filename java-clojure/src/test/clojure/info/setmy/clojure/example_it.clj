(ns info.setmy.clojure.example-it
    (:require [clojure.test :refer :all]
              [info.setmy.clojure.example :refer :all]
              [clojure.tools.logging :as log :refer :all]))

(def add +)

(deftest integration-test-add
    "Unit Test documentation"
  (testing "Greet test"
    (is (= 5 (add 3 2)))
    (is (= "He" (greet "Hello, Me")))))
