; Register system
(asdf:already-loaded-systems)
(pushnew (truename "./") ql:*local-project-directories*)

; Load system
;(require "cl-commons")
;(asdf:load-asd (merge-pathnames "cl-commons.asd" (uiop:getcwd)))
(ql:quickload :cl-commons)
(asdf:already-loaded-systems)

; Load tests system
(ql:quickload :rove)
;(require "cl-commons/tests")
(ql:quickload :cl-commons/tests)
(asdf:already-loaded-systems)

; Run tests
(rove:run :cl-commons/tests)

; Make executable
;(asdf:make :cl-commons)

(quit)
