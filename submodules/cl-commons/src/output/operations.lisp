;;;; Output (stdout, stderr, ...) operations.

(in-package :cl-user)
(defpackage cl-commons/output/operations
  (:use :cl)
  (:export :println))
(in-package :cl-commons/output/operations)


(defun println (format-string &rest lists)
    "Print line"
    (let* (
            (new-format-string (concatenate 'string format-string "#\linefeed"))
            (result-string (apply #'format nil new-format-string lists))
        )
        (print result-string)
    )
)
