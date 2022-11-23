(defpackage :info.setmy.lesson
  ;(:use :common-lisp)
  )
;(in-package :info.setmy.lesson)

(defun println (str)
  "Print line"
  (format t (concatenate 'string str "~%")))

(defun formatm (str result)
  "Shorter formating func"
  (format t (concatenate 'string str " = ~d ~%") result))

(defun show-math ()
  "Showcalculations"
  (format t "(expt 4 2) = ~d ~%" (expt 4 2))
  (format t "(sqrt 81) = ~d ~%" (sqrt 81))
  (format t "(exp 1) = ~d ~%" (exp 1))
  (format t "(log 1000 10) = ~d ~%" (log 1000 10))
  (format t "(eq 'dog 'dog) = ~d ~%" (eq 'dog 'dog))
  
  (format t "(floor 5.5) = ~d ~%" (floor 5.5))
  (format t "(ceiling 5.5) = ~d ~%" (ceiling 5.5))
  (format t "(max 2 4 6 10) = ~d ~%" (max 2 4 6 10))
  (format t "(min 2 4 6 10) = ~d ~%" (min 2 4 6 10))
  (format t "(oddp 15) = ~d ~%" (oddp 15))
  (format t "(evenp 15) = ~d ~%" (evenp 15))
  (format t "(numberp 2) = ~d ~%" (numberp 2))
  (format t "(eq nil nil) = ~d ~%" (eq nil nil))
  
  (format t "(+ 5 4) = ~d ~%" (+ 5 4))
  (format t "(- 5 4) = ~d ~%" (- 5 4))
  (format t "(* 5 4) = ~d ~%" (* 5 4))
  (format t "(/ 5 4) = ~d ~%" (/ 5 4))
  (format t "(/ 5 4.0) = ~d ~%" (/ 5 4.0))
  (format t "(rem 5 4) = ~d ~%" (rem 5 4))
  (format t "(mod 5 4) = ~d ~%" (mod 5 4))
  (format t "(mod 5 3) = ~d ~%" (mod 5 3))
  
  (formatm "(equal 5 3)" (equal 5 3))
  (formatm "(equal 'foo 'bar)" (equal 'foo 'bar))
  (formatm "(equal 5 5)" (equal 5 5))
  (formatm "(equal 5.2 5.3)" (equal 5.2 5.3))
  (formatm "(equal \"string\" \"String\")" (equal "string" "String"))
  (formatm "(equal \"String\" \"String\")" (equal "String" "String"))
  (formatm "(equal (list 1 2 3) (list 1 2 3))" (equal (list 1 2 3) (list 1 2 3)))
  (formatm "(equal (list 1 2 3) (list 1 3 2))" (equal (list 1 2 3) (list 1 3 2)))

  (format t "Package: ~a ~%" *package*)
  (format t "Package: ~a ~%" common-lisp:*package*)
  (format t "Package: ~a ~%" cl:*package*)
  )

(defvar *name* "Imre T")

(defun show-variables ()
  "Show variables"
  (let ((x 2)
         (y 10) z)
    (format t "~a : ~a : ~a~%" x y z))
  (format t "~a~%" *name*))
