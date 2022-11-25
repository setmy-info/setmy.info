(require "asdf")

(format t "ASDF version: ~a ~1%" (asdf:asdf-version))

(load #p"./src/style.lisp")
(load #p"./src/foo.lisp")
(load #p"./src/lesson.lisp")
;; TODO : ASDF systems
;; (asdf:load-system :foo)

(defpackage first-app
  ;(:use :cl :first-app/foo :first-app/lesson))
  (:use :cl)
  (:import-from :first-app/foo :hello-world)
  (:import-from ::first-app/lesson show-math))
(in-package :first-app)

(hello-world)
(show-math)
