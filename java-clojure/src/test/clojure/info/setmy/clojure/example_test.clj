(ns info.setmy.clojure.example-test
    (:require [clojure.test :refer :all]
              [info.setmy.clojure.example :refer :all]
              [clojure.tools.logging :as log :refer :all]))

(def add +)

(deftest unit-test-add
    "Integration Test documentation"
  (testing "Greet test"
    (is (= 5 (add 3 2)))
    (is (= "Hello, Me" (greet "Me")))))
