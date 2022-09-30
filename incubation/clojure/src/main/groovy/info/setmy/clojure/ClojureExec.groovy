package info.setmy.clojure

import groovy.transform.ToString
import groovy.transform.builder.Builder

@Builder
@ToString
class ClojureExec {
    String ns
    String scriptName
    String mainFunctionName
    String [] args
}
