(defproject info.setmy/foo-bar-lib-probe "1.0.0"
    :description "Example clojars foo project probe"
    :url
    "https://github.com/setmy-info/setmy.info/tree/develop/incubation/foo-bar-lib-probe"
    :license
    {:name "MIT License"
     :url  "https://opensource.org/licenses/MIT"}
    :dependencies [[org.clojure/clojure "1.11.1"]]
    :plugins [[lein-codox "0.10.8"]]
    :main ^:skip-aot foo-bar-lib-probe.core
    :target-path "target/%s"
    :profiles
    {:uberjar {:aot      :all
               :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}}
    :deploy-repositories
    [["clojars"
      {:url           "https://clojars.org/repo"
       :sign-releases false}]])
