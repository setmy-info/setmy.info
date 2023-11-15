# cl-commons

[A short, one-line description of the project]

# Overview

[A longer description of the project, optionally with sub-sections like
'Features', 'History', 'Motivation', etc.]

## Usage

## Installation

## Usage, tips and tricks

### Setup

Have to download, install and load quicklisp into SBCL - in (LISP quide)[https://setmy-info.github.io/src/site/markdown/lisp.html].

### Execution

```sh
sbcl
```

Building with testing part

```clojure
; Register system
(asdf:already-loaded-systems)
(pushnew (truename "./") ql:*local-project-directories*)
(ql:register-local-projects)

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

(quit)
```

Or execute building script:

```sh
sbcl --load builder.lisp
```

## Derive new project

1. Search and replace in all files **cl-commons** with new project (system) marker.
2. Rename files **cl-commons.asd**, **cl-commons.cmd** and **cl-commons** with project related names.

```clojure
(ql:where-is-system "hunchentoot")
```

/home/has/quicklisp/dists/quicklisp/software/hunchentoot-v1.3.0/

## See also

[LISP know how](https://setmy-info.github.io/src/site/markdown/lisp.html)

## Author

* Imre Tabur <info@setmy.info>

## Copyright

Copyright (c) 2023 etc Imre Tabur <info@setmy.info>

## License

Licensed under the MIT License.
