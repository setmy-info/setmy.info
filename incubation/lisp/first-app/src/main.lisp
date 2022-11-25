;;;; Application main execution file.

(in-package :cl-user)
(defpackage first-app/main
  (:use :cl)
  (:import-from :first-app/foo :hello-world)
  (:import-from :first-app/style :+golden-ratio+)
  (:import-from :first-app/lesson :show-math)
  (:export :show-math))
(in-package :first-app/main)

(format t "~%ASDF version: ~a ~1%" (asdf:asdf-version))

(hello-world)
(show-math)
