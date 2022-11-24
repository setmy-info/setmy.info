(defpackage :info.setmy.main
  ;(:use :common-lisp :info.setmy.lesson :info.setmy.foo)
  )
;(in-package :info.setmy.main)

(require "asdf")

(load #p"./src/lisp/foo/foo.lisp")
;; TODO : ASDF systems
;; (asdf:load-system :foo)

(load #p"./src/lisp/lessons/lesson.lisp")

(format t "ASDF version: ~a ~1%" (asdf:asdf-version))

(hello-world)
(show-math)