package info.setmy.groovy.example

import groovy.transform.NamedParam
import groovy.transform.NamedParams

import java.util.concurrent.ConcurrentHashMap

import static groovy.lang.Closure.DELEGATE_FIRST
import static groovy.lang.Closure.DELEGATE_ONLY

class Dsl {

    static void pipeline(@DelegatesTo(value = PipelineDsl, strategy = DELEGATE_ONLY) final Closure closure) {
        final PipelineDsl pipelineDsl = new PipelineDsl()
        closure.delegate = pipelineDsl
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()
    }
}

class PipelineDsl {

    final AgentType any = AgentType.ANY
    static final Map env = [:] as ConcurrentHashMap

    void agent(final AgentType agentType) {
        println "Running agent with ${agentType}"
    }

    void environment(@DelegatesTo(value = Map, strategy = DELEGATE_FIRST) final Closure closure) {
        env.with(closure)
        println "Running agent with ${env}"
    }

    void stages(@DelegatesTo(value = StagesDsl, strategy = DELEGATE_ONLY) final Closure closure) {
        final StagesDsl stagesDsl = new StagesDsl()
        closure.delegate = stagesDsl
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()
        stagesDsl.stages.each { it.run() }
    }
}

enum AgentType {
    ANY
}

class StagesDsl {

    protected final List<Stage> stages = []

    void stage(final String name, @DelegatesTo(value = StageDsl, strategy = DELEGATE_ONLY) final Closure closure) {
        stages << new Stage(name, closure)
    }
}

class Stage {

    String name
    Closure closure

    Stage(String name, Closure closure) {
        this.name = name
        this.closure = closure
    }

    void run() {
        println "Running stage: ${name}"

        final def stageDsl = new StageDsl()
        closure.delegate = stageDsl
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()
    }
}

class StageDsl {
    void steps(@DelegatesTo(value = Steps, strategy = DELEGATE_ONLY) final Closure closure) {
        final def steps = new Steps()
        closure.delegate = steps
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()
    }
}

class Steps {
    void sh(final String txt) {
        println "Shell: ${txt}"
        sh(script: txt, returnStdout: false)
    }

    Object sh(@NamedParams([
        @NamedParam(value = "script", type = String, required = true),
        @NamedParam(value = "returnStdout", type = Boolean)
    ]) final Map params) {
        final Process process = params.script.toString().execute()
        process.waitFor()
        println "-> ${params.script}"
        if (process.exitValue() == 0) {
            if (params.returnStdout) {
                return process.text
            }
            println process.text
        } else {
            println process.err.text
        }
    }

    void echo(final String txt) {
        println "${txt}"
    }
}
