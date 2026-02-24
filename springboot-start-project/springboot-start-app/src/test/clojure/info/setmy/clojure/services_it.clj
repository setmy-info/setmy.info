(ns info.setmy.clojure.services-it
    (:require [clojure.test :refer :all]))

(def add +)

(deftest integration-test-add
    "Test documentation"
  (testing "Adding test"
    (is (= 5 (add 3 2)))))
