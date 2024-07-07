(ns info.setmy.core
    (:require [clojure.string :as str])
    ;(:require [info.setmy.services.greet :as greet])
    ;(:require [info.setmy.services.barService :as barService])
    ; (:require [info.setmy.services.getBar :as getBar])
    (:gen-class)
    ; TODO : remove "use", prefer require
    (:use info.setmy.services))

(def aString "String")
(def aLong 14)
(def aBoolean true)
(def aDouble 11.12345)
(def aXyz)
(def aRatioFraction 1/2)
(def aBigInt 15N)
(def aBigDecimal 16M)

(println "Boolean type: " (type false))
(println "aString type: " (type aString))
(println "aLong type: " (type aLong))
(println "aDouble type: " (type aDouble))
(println "aBoolean type: " (type aBoolean))
(println "aRatioFraction type: " (type aRatioFraction))
(println "aBigInt type: " (type aBigInt))
(println "aBigDecimal type: " (type aBigDecimal))

(defn ishe
    [name]
    (if (= name "Minime")
        (println "Yes, he is Minime")
        (println "No, he is not Minime")))

(defn return-true [] true)

(defn -main
    "I don't do a whole lot ... yet."
    [& args]

    (println "Hello, World!")

    ;;
    (println "Hello World from Clojure!")
    (println "System: " (.. System (getProperties) (get "os.name")))
    (println "Java: " (.. info.setmy.clojure.BarService (getFoo)))
    (println "Java Service: " (.. info.setmy.clojure.BarService/barService (getBar "Imre")))
    (println "Service function: " (getBar "Imre"))
    (println "Hello World from Clojure!")
    (println (greet "Imre"))
    (println "Java main called clojure function with args: "
             (apply str (interpose " " args)))
    ;;
    (println "aLong Nil?: " (nil? aLong))
    (println "aXyz Nil?: " (nil? aXyz))
    ;(println "nothing Nil?: " (nil? nothing)) ; wont work:Syntax error compiling at (tutorial\core.clj:20:31). ;Unable to resolve symbol: nothing in this context
    (println "(pos? 15): " (pos? 15))
    (println "(neg? 15): " (neg? 15))
    (println "(pos? -15): " (pos? -15))
    (println "(neg? -15): " (neg? -15))
    (println "(odd? 12): " (odd? 12))
    (println "(odd? 13): " (odd? 13))
    (println "(even? 12): " (even? 12))
    (println "(even? 13): " (even? 13))
    (println "(zero? 0): " (zero? 0))
    (println "(integer? aLong): " (integer? aLong))
    (println "(double? aDouble): " (double? aDouble))
    (println "(ratio? aRatioFraction): " (ratio? aRatioFraction))

    (def aString "Hello World")
    (println (format "This is string %s" aString))
    (println (format "4 spaces and %4d" aLong))
    (println (format "Leading zeros %04d" aLong))
    (println (format "%-4d left justified" aLong))
    (println (format "3 decimals %.3f" aDouble))

    (def aString2 " As expected, this is example string. It is 2nd example \r\n . ")
    (println "Blank 1: " (str/blank? aString2))
    (println "Blank 2: " (str/blank? ""))
    (println "Blank 3: " (str/blank? " "))
    (println "includes: " (str/includes? aString2 "example"))
    (println "index: " (str/index-of aString2 "example"))

    (println "Splitted by spaces: " (str/split aString2 #" "))
    (println "Splitted d: " (str/split aString2 #"\d"))
    (println "join: " (str/join " " ["Dog" "Duck" "Cat"]))
    (println "replace: " (str/replace aString2 #"2nd" "3d"))
    (println "trim: " (str/trim aString2))
    (println "triml: " (str/triml aString2))
    (println "trimr: " (str/trimr aString2))
    (println "trim-newline: " (str/trim-newline aString2))
    (println "upper-case: " (str/upper-case (str/trim-newline aString2)))
    (println (format "Trim '%s'" (str/trim (str/trim-newline aString2))))

    (def aList (list 1 2 3 4 5))
    (println "aList type: " (type aList))
    (def aDifferencesList (list "Dog" 1, 3.1415 true "Dog"))
    (println aDifferencesList)
    (println aList)
    (println "First: " (first aList))
    (println "Last: " (last aList))
    (println "n-th: " (nth aList 2))
    (println "Added: " (list* 1 2 [3 4]))
    ;;(println "Added 2: " (list* aList [6 7])) ;doesnt work
    (println "Add to left: " (cons 0 aList))

    (def aSet (set '(1 2 2 3 4 4 5)))
    (println "aSet type: " (type aSet))
    (println aSet)
    (println "6: " (get aSet 6))
    (println "5: " (get aSet 5))
    (println (conj aSet 6))
    (println "aSet" aSet)
    (println "aSet contains 6" (contains? aSet 6))
    (println "aSet contains 5" (contains? aSet 5))
    (println "aSet removed 5" (disj aSet 5))

    (def aVector (vector 1 true 2 2 3 4 4 5 "Dog"))
    (println "aVector type: " (type aVector))
    (println aVector)
    (println "Vector 6: " (get aVector 6))
    (println "Vector pop: " (pop aVector))
    (println "Vector sub: " (subvec aVector 1 7))

    (def aHashMap (hash-map "firstName" "Imre" "lastName" "Tabur"))
    (println "aHashMap type: " (type aHashMap))
    (println aHashMap)
    (println "First name: " (get aHashMap "firstName"))
    (println "Find: " (find aHashMap "lastName"))
    (println "Contains 1: " (contains? aHashMap "nonExistingKey"))
    (println "Contains 2: " (contains? aHashMap "firstName"))
    (println "Keys: " (keys aHashMap))
    (println "Vals: " (vals aHashMap))

    (def aSortedMap (sorted-map "firstName" "Imre" "lastName" "Tabur" "age" 46))
    (println "aSortedMap type: " (type aSortedMap))
    (println aSortedMap)

    (println "sum: " (+ 1 2 3))
    (println "minus: " (- 1 2 3))
    (println "minus: " (- 1 2 2.2 1/2))
    (println "*: " (* 5 2 3))
    (println "/: " (/ 10 5 3))
    (println "mod: " (mod 12 5))
    (println "inc: " (inc 5))
    (println "dec: " (dec 5))

    (println "Minime: " (ishe "Minime"))
    (println "Minime: " (ishe "Me"))
    (println "Returned: " (return-true))

    ; Generates a class: public final class info.setmy.core.Person implements IRecord, IHashEq, IObj, ILookup, IKeywordLookup, IPersistentMap, Map, Serializable
    (defrecord Person [firstName lastName])
    (def person1 (->Person "Imre" "Tabur"))
    (println (:firstName person1))

    ;sequence of strings to integer list
    (map #(Integer/parseInt %) ["1" "2" "3" "4"])
    ; add security risk
    (map read-string ["1" "2.2" "3/4" "true"]))
