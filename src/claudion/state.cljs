(ns claudion.state
  (:require [reagent.core :as r]))

(defonce selected (r/atom nil))

(def potions-spent (r/atom 0))

;; TODO add to list removed stones when state is updated
(defonce undo-list [])

(def initial-board {[2 -1 -1] {:type "wealth" :variant "tin"}
                    [0 -2 2]  {:type "wealth" :variant "iron"}
                    [-3 -1 4] {:type "wealth" :variant "garnet"}
                    [-4 3 1]  {:type "wealth" :variant "jade"}
                    [0 2 -2]  {:type "wealth" :variant "aquamarine"}
                    [0 0 0]   {:type "wealth" :variant "gold"}
                    [-1 4 -3] {:type "potion" :variant "potion"}
                    [-1 -4 5] {:type "potion" :variant "potion"}
                    [-3 4 -1] {:type "potion" :variant "potion"}
                    [-1 1 0]  {:type "potion" :variant "potion"}
                    [-4 1 3]  {:type "potion" :variant "potion"}
                    [1 4 -5]  {:type "life" :variant "vibrant"}
                    [4 -5 1]  {:type "life" :variant "vibrant"}
                    [-5 1 4]  {:type "life" :variant "vibrant"}
                    [-2 2 0]  {:type "life" :variant "vibrant"}
                    [2 2 -4]  {:type "life" :variant "pale"}
                    [-2 -2 4] {:type "life" :variant "pale"}
                    [-1 0 1]  {:type "life" :variant "pale"}
                    [4 0 -4]  {:type "life" :variant "pale"}
                    [0 4 -4]  {:type "colour" :variant "blue"}
                    [1 3 -4]  {:type "colour" :variant "red"}
                    [3 1 -4]  {:type "colour" :variant "green"}
                    [5 -1 -4] {:type "colour" :variant "green"}
                    [0 3 -3]  {:type "colour" :variant "green"}
                    [3 0 -3]  {:type "colour" :variant "purple"}
                    [4 -1 -3] {:type "colour" :variant "purple"}
                    [-2 4 -2] {:type "colour" :variant "green"}
                    [1 1 -2]  {:type "colour" :variant "blue"}
                    [2 0 -2]  {:type "colour" :variant "red"}
                    [4 -2 -2] {:type "colour" :variant "blue"}
                    [-4 5 1]  {:type "colour" :variant "wildcard"}
                    [-1 2 -1] {:type "colour" :variant "purple"}
                    [0 1 -1]  {:type "colour" :variant "blue"}
                    [1 0 -1]  {:type "colour" :variant "blue"}
                    [4 -3 -1] {:type "colour" :variant "wildcard"}
                    [-3 5 -2] {:type "colour" :variant "wildcard"}
                    [-4 4 0]  {:type "colour" :variant "purple"}
                    [-3 3 0]  {:type "colour" :variant "purple"}
                    [1 -1 0]  {:type "colour" :variant "red"}
                    [2 -2 0]  {:type "colour" :variant "green"}
                    [3 -3 0]  {:type "colour" :variant "blue"}
                    [4 -4 0]  {:type "colour" :variant "wildcard"}
                    [-2 1 1]  {:type "colour" :variant "wildcard"}
                    [0 -1 1]  {:type "colour" :variant "red"}
                    [1 -2 1]  {:type "colour" :variant "red"}
                    [3 -4 1]  {:type "colour" :variant "red"}
                    [-4 2 2]  {:type "colour" :variant "green"}
                    [-2 0 2]  {:type "colour" :variant "purple"}
                    [-1 -1 2] {:type "colour" :variant "purple"}
                    [2 -4 2]  {:type "colour" :variant "red"}
                    [-3 0 3]  {:type "colour" :variant "blue"}
                    [0 -3 3]  {:type "colour" :variant "green"}
                    [1 -4 3]  {:type "colour" :variant "green"}
                    [-4 0 4]  {:type "colour" :variant "purple"}
                    [-1 -3 4] {:type "colour" :variant "red"}
                    [0 -4 4]  {:type "colour" :variant "blue"}})

(def stones (r/atom initial-board))
