;;;; quicklisp and ASDF loader and execution file.

(pushnew (truename "c:/sources/setmy.info/incubation/lisp/first-app") ql:*local-project-directories* )
(ql:register-local-projects)
(ql:quickload :first-app)
(quit)
