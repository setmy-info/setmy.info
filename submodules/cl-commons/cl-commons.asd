; Main system definition
(defsystem "cl-commons"
  :version "0.0.0"
  :author "Imre Tabur <info@setmy.info>"
  :maintainer "Imre Tabur <info@setmy.info>"
  :homepage "https://github.com/setmy-info/cl-commons"
  :license "MIT"
  :depends-on ("alexandria")
  :components ((:module "src"
                :components
                (
                  (:file "collections/operations")
                  (:file "output/operations")
                  ;(:file "main" :depends-on ("style" "lesson" "foo"))
                )))
  :build-operation "program-op" ;; leave as is
  ;:build-pathname "cl-commons.bin"
  ;:entry-point "cl-commons/main:main"
  :description "A Lisp commons library."
  :in-order-to ((test-op (test-op "cl-commons/tests"))))

; Testing system definition
(defsystem "cl-commons/tests"
  :author "imret"
  :license "MIT"
  :depends-on ("cl-commons"
               "rove")
  :components ((:module "tests"
                :components
                ( 
                  (:file "collections/operations")
                  (:file "output/operations")
                )
              ))
  :description "Test system for cl-commons"
  :perform (test-op (op c) (symbol-call :rove :run c)))
