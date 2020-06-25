(ns matching-brackets.core
  (:gen-class)
  (:require [clojure.string :refer [blank?]]
            [clojure.core :refer [subs first]]
  )
)
; ---------------------
(def opening_parentheses (char 40))
(def closing_parentheses (char 41))
(defn consume_char [counter char]
  (
    cond
    (= char opening_parentheses) (inc counter)
    (= char closing_parentheses) (dec counter)
    :else counter
  )
)

(defn parentheses_match [counter string]
  (
    cond
    (blank? string) (zero? counter)
    :else ( 
      let [new_counter (consume_char counter (first string))]
      (if (< new_counter 0) false (parentheses_match new_counter (subs string 1)))
    )
  )
)
; ---------------------
(defn -main
  "verifies that parantheses in a string are matched and nested correctly"
  [& args]
  (println "Enter expression:")
  (println (parentheses_match 0 (read-line))
  )
)
