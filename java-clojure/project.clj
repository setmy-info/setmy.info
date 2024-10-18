(defproject info.setmy/java-clojure "1.2.0-SNAPSHOT"
    :description "Java Clojure project."
    :url "https://github.com/setmy-info/setmy.info/java-clojure"
    :license
    {:name "MIT License"
     :url  "https://mit-license.org/"}
    :dependencies [[org.clojure/clojure "1.11.1"]
                   [org.clojure/tools.cli "1.0.219"]
                   [org.clojure/data.json "2.4.0"]]
    :plugins [[lein-codox "0.10.8"]]
    :main ^:skip-aot info.setmy.clojure
    :target-path "target/%s"
    :source-paths ["src/main/clojure"]
    :test-paths ["src/test/clojure"]
    :java-source-paths ["src/main/java"] ; Java source is stored separately.
    :resource-paths ["src/main/resources" "./target/classes"] ; Non-code files included in classpath/jar.
    :profiles
    ; with-profile :abc
    {:uberjar {:aot      :all
               :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}
     :local   {}
     :dev     {}
     :ci      {}
     :test    {:resource-paths ["src/test/resources"]}
     :prelive {}
     :live    {}}
    :deploy-repositories
    [["clojars"
      {:url           "https://clojars.org/repo"
       :sign-releases false}]]
    :aliases {"doc" ["do" "codox"]}
    :codox
    {:metadata {:doc/format :markdown}})
