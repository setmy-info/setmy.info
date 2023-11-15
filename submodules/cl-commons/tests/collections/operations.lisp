(in-package :cl-user)
(defpackage cl-commons/tests/collections/operations
  (:use :cl
        :rove)
        (:import-from :cl-commons/collections/operations :apply-concat-many)
        (:import-from :cl-commons/collections/operations :product-as-pairs)
        )
(in-package :cl-commons/tests/collections/operations)

(deftest test-apply-concat-many-1
  (testing "should apply-concat-many many lists together"
  (let 
    (
      (result-list   (apply-concat-many (list 1 2 3) (list 4 5 6)))
      (result-list-2 (apply-concat-many '()))
      (result-list-3 (apply-concat-many nil))
    )
    (print 
      (format
        nil
        "Hello world: ~s.~%"
        result-list))
    (ok (equal result-list   '(1 2 3 4 5 6)))
    (ok (equal result-list-2 '()))
    (ok (equal result-list-3 '()))
  )))
