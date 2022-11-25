;;;; Collected code lessons and examples.

(in-package :cl-user)
(defpackage first-app/lesson
  (:use :cl)
  (:export :show-math))
(in-package :first-app/lesson)

(defun println (str)
  "Print line"
  (format t (concatenate 'string str "~%")))

(defun formatm (str result)
  "Shorter formating func"
  (format t (concatenate 'string str " = ~d ~%") result))

; Can be without value
(defvar *no-value*)
(defvar *a*)

; Can't be without value
(defparameter *with-value* 123)

(defvar *persons-hash-table*)
(setq *persons-hash-table* (make-hash-table))
(setf (gethash '001 *persons-hash-table*) '(Konstantin Päts))
(setf (gethash '002 *persons-hash-table*) '(Lennart Meri))
(setf (gethash '003 *persons-hash-table*) '(Arnold Rüütel))
(setf (gethash '004 *persons-hash-table*) '(Toomas Hendrik Ilves))
(setf (gethash '005 *persons-hash-table*) '(Kersti Kaljulaid))
(setf (gethash '006 *persons-hash-table*) '(Alar Karis))

; Like array in Java, C/C++. One dimensinal fixed size.
(defparameter *initialized-vector* (vector 1 2 3))

; Creates array for 5 elements, looks 2 size with NIL filled and made as rezisable - without 
; asjustable it can crow only to 5 elements.
(defparameter *initialized-array* (make-array 5 :initial-element nil :fill-pointer 2 :adjustable T))

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

  (format t "Global value: ~d ~%" *with-value*)

  (maphash #'(lambda (key value) (format t "~a => ~a~%" key value)) *persons-hash-table*)
  (format t "initialized-vector: ~a ~%" *initialized-vector*)
  
  (format t "initialized-array: ~a ~%" *initialized-array*)
  (vector-push 1 *initialized-array*)
  (format t "initialized-array: ~a ~%" *initialized-array*)
  (vector-pop *initialized-array*)
  (format t "initialized-array: ~a ~%" *initialized-array*)

  (format t "mapped vectors: ~a ~%"
    (map 'vector #'* #(1 2 3 4 5) #(10 9 8 7 6)))

  (map-into *initialized-array* #'+ #(5 6 7))
  (format t "mapped into: ~a ~%" *initialized-array*)

  (format t "Reduced: ~a ~%"
    (reduce #'+ #(1 2 3 4 5 6 7 8 9 10)))
  )

(defvar *name* "Imre T")

(defun show-variables ()
  "Show variables"
  (let ((x 2)
         (y 10) z)
    (format t "~a : ~a : ~a~%" x y z))
  (format t "~a~%" *name*))
