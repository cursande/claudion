(ns claudion.state
  (:require [reagent.core :as r]))

(defonce selected (r/atom nil))

(def stones (r/atom {[0 5 -5]  {:type "colour" :variant "green"}
                     [2 3 -5]  {:type "wildcard" :variant "all"}
                     [-1 4 -3] {:type "colour" :variant "pink"}
                     [1 3 -4]  {:type "colour" :variant "blue"}
                     [1 2 -3]  {:type "colour" :variant "green"}
                     [0 3 -3]  {:type "colour" :variant "purple"}
                     [2 1 -3]  {:type "colour" :variant "purple"}
                     [2 2 -4]  {:type "colour" :variant "pink"}
                     [3 1 -4]  {:type "colour" :variant "blue"}
                     [5 -1 -4] {:type "colour" :variant "purple"}
                     [1 1 -2]  {:type "wildcard" :variant "all"}}))
