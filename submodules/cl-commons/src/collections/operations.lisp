;;;; Collection operations.

(in-package :cl-user)
(defpackage cl-commons/collections/operations
  (:use :cl)
  (:export :apply-concat-many)
  (:export :product)
  (:export :product-as-pairs))
(in-package :cl-commons/collections/operations)

(defun apply-concat-many (&rest lists)
    "Concatenate many lists together"
    (let (
        (input-list (or lists '()))
        (result-list '()))
      (loop for a-list in input-list
        do (setq result-list (append result-list a-list)))
      result-list))

(defun product (list-a list-b &rest product-function)
    "Product function can be also for example + and str (concatenate strings)"
    nil
    ;(let ((product-function (or product-function *)))
    ;    (loop for x in list-a do (
    ;      loop for y in list-b
    ;      do (product-function x y))))
)

(defun product-as-pairs (dimension-a dimension-b)
    ""
    nil
    ;(for [x dimension-a
    ;      y dimension-b]
    ;    [x y])
    ;(loop for x in dimension-a
    ;   (loop for y in dimension-b
    ;               collect '(x y)))

    ;(loop for i from 0 below n do
    ;  (loop for j from 0 below m do
    ;    (format t "a[~a ~a] = ~a~%" i j (aref a i j)))))
)
