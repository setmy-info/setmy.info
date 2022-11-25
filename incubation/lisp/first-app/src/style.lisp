;;;; Top comment with 4 semicolons with explanation of file purpose. Line length 80-100.

(in-package :cl-user)
(defpackage first-app/style
  (:use :cl))
  #|Avoid :use. Instead, do this:
  (:use :cl)
  (:import-from :first-app/foo :hello-world)
  (:import-from ::first-app/lesson show-math))
  Use :import-from
  |#
(in-package :first-app/style)

;;; Max line length --------------------------------------------------------------------------------

(defparameter *global-variable* "Global variable in earmuffs. All variables should be writen 
    complete words. You should use lower case. You must not use / or . instead of -")

;;; Region 1. Three semicolons.

(defconstant +golden-ratio+ 575 "Global constants should be with plus sign.")
(defconstant +mix32+ #x12b9b0a1 "pi, an arbitrary number")
(defconstant +mix64+ #x2b992ddfa23249d6 "more digits of pi")

;; Short comment.
(defun is-it-good-p (input-data)
    "Function that returns T or NIL should end with -p (predicate). If the rest of the function 
    name is a single word, e.g: abstractp, bluep, evenp. If the rest of the function name is more 
    than one word, e.g largest-planet-p, request-throttled-p."
    ;; Region 2. Two semicolons.
    nil)

; Short note.

; Comments should end with period.

; Do not comment code out. Erase it and use version control. But for now for code examples ...

  #| Instead:
  (if (engine-running-p car)
    (drive car))

  (if (not (seatbelts-fastened-p car))
    (warn-passengers car))

    Should be:
  (when (engine-running-p car)
    (drive car))

  (unless (seatbelts-fastened-p car)
   (warn-passengers car))
  |#

;;--- TODO(info@setmy.info): Refactor to provide a better API.  Remove this code after release 1.7
;; or before 2099-12-31.
(defun foo-bar ()
    (format t "Hello, world~1%"))
