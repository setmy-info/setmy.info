#!/bin/sh

clj -M --main cljs.main --compile hello-world.core --repl

exit ${?}
