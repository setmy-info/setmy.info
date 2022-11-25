; Main system definition
(defsystem "first-app"
  :version "0.1.0"
  :author "Imre Tabur <info@setmy.info>"
  :maintainer "Imre Tabur <info@setmy.info>"
  :homepage "https://setmy-info.github.io/src/site/markdown/lisp.html"
  :license "MIT"
  :depends-on ("alexandria")
  :components ((:module "src"
                :components
                (
                  (:file "foo")
                  (:file "lesson")
                  (:file "style")
                  (:file "main" :depends-on ("style" "lesson" "foo"))
                )))
  :description "A sample Lisp system(project)."
  :in-order-to ((test-op (test-op "first-app/tests"))))

; Testing system definition
(defsystem "first-app/tests"
  :author "imret"
  :license "MIT"
  :depends-on ("first-app"
               "rove")
  :components ((:module "tests"
                :components
                ((:file "main"))))
  :description "Test system for first-app"
  :perform (test-op (op c) (symbol-call :rove :run c)))
