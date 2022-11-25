;;;; Just simple Hello world function example.

(in-package :cl-user)
(defpackage first-app/foo
  (:use :cl)
  (:export :hello-world))
(in-package :first-app/foo)

(defun hello-world ()
  (format t "Hello, world~1%"))
