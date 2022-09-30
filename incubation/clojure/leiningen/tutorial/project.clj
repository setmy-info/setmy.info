(defproject tutorial "0.1.0-SNAPSHOT"
    :description "FIXME: write description"
    :url "http://example.com/FIXME"
    :license
    {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
     :url  "https://www.eclipse.org/legal/epl-2.0/"}
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
