package info.setmy.microservice.clojure.service

import groovy.transform.TupleConstructor

@TupleConstructor
class ClojureExec {
    String ns
    String scriptName
    String mainFunctionName
    String[] args
}
