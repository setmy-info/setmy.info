;;;; tokenization helper functions

(in-package :cl-user)
(defpackage cl-commons/tokens/operations
  (:use :cl)
  (:export :println))
(in-package :cl-commons/tokens/operations)

(defun token (tocen)
    ;(list :token tocen)
    (list tocen)
    )

(defun extend-token (a-list key value)
    ;(append tocen (list key value)
    ;(setq a-list (cons key a-list))
    (setq a-list (cons value a-list))
    )

(defparameter *a-list* '())

(setq *a-list* (cons "Hello" *a-list*))
(setq *a-list* (cons "World" *a-list*))

(extend-token (token "Hello") "World")
