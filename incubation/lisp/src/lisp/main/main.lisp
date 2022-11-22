(require "asdf")
(format t "ASDF version: ~a ~1%" (asdf:asdf-version))

(load #p"./src/lisp/foo/foo.lisp")
;; TODO : ASDF systems
;; (asdf:load-system :foo)

(hello-world)
