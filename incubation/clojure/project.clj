(defproject tutorial "0.0.0-SNAPSHOT"
    :description "Incubation Clojure project"
    :url "http://example.com/FIXME"
    :license
    {:name "MIT"
     :url  "https://mit-license.org/"}
    :dependencies [[org.clojure/clojure "1.11.1"]]
    :main ^:skip-aot tutorial.core
    :target-path "target/%s"
    :source-paths ["src" "src/main/clojure"]
    :test-paths ["test" "src/test/clojure"]
    :java-source-paths ["src/main/java"] ; Java source is stored separately.
    :resource-paths ["src/main/resource"] ; Non-code files included in classpath/jar.
    :profiles
    {:uberjar {:aot      :all
               :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
