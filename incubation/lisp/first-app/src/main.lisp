(require "asdf")

(format t "ASDF version: ~a ~1%" (asdf:asdf-version))

(load #p"./src/foo.lisp")
(load #p"./src/lesson.lisp")
;; TODO : ASDF systems
;; (asdf:load-system :foo)

(defpackage first-app
  (:use :cl :first-app/foo :first-app/lesson))
(in-package :first-app)

(hello-world)
(show-math)
