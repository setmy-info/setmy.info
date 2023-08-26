(ns foo-bar-lib-probe.core
    (:gen-class))

(def
    ^{:doc   "A hashmap of functions to be applied to each file."
      :value {:abs-name-fn "A function that takes a file path and returns its absolute path."
              :process-fn  "A function that processes each file."}}
    file-functions
    {})

(defn foo
    "Sum of two numbers.

     Adds the given numbers together and returns the result.

     Parameters:
       - a (number): The first number to add.
       - b (number): The second number to add.

     Returns:
       The sum of the two input numbers.

     See also:
        - [@file-functions]: Documentation for available file functions.
        - [Google](http://www.google.com): A popular search engine."
    [a b]
    (+ a b))

(defn -main
    "Leiningen clojars project example"
    [& args]
    (let [sum (foo 5 7)]
        (println (str "Sum: " sum))
        (println "Hello, World!")))
