; as folder name
(defsystem "lisp"
  :description "lisp: a sample Lisp system(project)."
  :version "0.0.1"
  :author "Imre Tabur <info@setmy.info>"
  :licence "MIT"
  :depends-on ("optima.ppcre" "command-line-arguments")
  :components ((:file "src")
               (:file "macros" :depends-on ("packages"))
               (:file "hello" :depends-on ("macros"))))
