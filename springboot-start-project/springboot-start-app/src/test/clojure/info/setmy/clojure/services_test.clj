(ns info.setmy.clojure.services-test
    (:require [clojure.test :refer :all]
              [info.setmy.clojure.services :refer :all]
              [clojure.tools.logging :as log :refer :all]
              [clojure.string :as str]))

(def add +)

(deftest test-add
    "Test documentation"
  (testing "Adding test"
    (log/info "Testing add with input " 3 " and " 2 ".")
    (is (= 5 (add 3 2)))
    (is (= "Hello, Imre" (greet "Imre")))
    (log/debug "Test result 3 + 2: " (add 3 2))
    (log/info "Testing add function with input 5 ja 2.")
    (is (= 7 (add 5 2)))
    (log/debug "Test results for 5 + 2: " (add 5 2))
    (when-not (= 7 (add 5 2))
      (log/error "Test failed: 5 + 2 is not equal to 7!"))
    (log/info "Testing add function with wrong input.")
    (is (= 8 (add 3 5)))
    (log/error "Error! 3 + 5 is not equal to 8, result is: " (add 3 5))))
