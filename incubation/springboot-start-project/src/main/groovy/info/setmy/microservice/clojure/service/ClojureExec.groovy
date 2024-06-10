package info.setmy.microservice.clojure.service

import groovy.transform.Immutable
import groovy.transform.ToString
import groovy.transform.builder.Builder

@Builder
@ToString
@Immutable
class ClojureExec {
    String ns
    String scriptName
    String mainFunctionName
    String [] args
}
