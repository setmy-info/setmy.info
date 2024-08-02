#!/opt/sbcl/bin/sbcl --script

(require :asdf)
(load "~/.quicklisp/setup.lisp")
(load "~/.sbclrc")

(ql:quickload "vecto")

(write-line (concatenate 'string "Version: " (asdf:asdf-version)))
(format t "~{~A~^  ~}" (asdf:registered-systems))
(write-line "")

(defparameter *a-list* (list 1 2 3 4))

(defparameter *maped-list* (maplist (lambda (x) x) *a-list*))
(format t "~{~A~^  ~}" *maped-list*)
(write-line "")

(defparameter *maped-list* (maplist (lambda (a-list) (car a-list)) *a-list*))
(format t "~{~A~^  ~}" *maped-list*)
(write-line "")

(defparameter *maped-list* (maplist (lambda (a-list) (+ (car a-list) 1)) *a-list*))
(format t "~{~A~^  ~}" *maped-list*)
(write-line "")

(defparameter *maped-list* (mapcar (lambda (a-list-item) (+ a-list-item 1)) *a-list*))
(format t "~{~A~^  ~}" *maped-list*)
(write-line "")

;(write-line (concatenate 'list (maplist #'(lambda (x) x) (list 1 2 3 4)))

