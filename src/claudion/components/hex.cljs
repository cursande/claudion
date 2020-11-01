(ns claudion.components.hex
  (:require [claudion.state :as state]
            [claudion.components.stone :as stone :refer [Stone]]))

(defn Hex
  "Represents each individual hex on the grid that a stone can be placed or
  removed from. Represented by cubic coordinates.
  y can be derived from `x` and `z` via the rule x + y + z = 0"
  [x z]
  (let [y  (- (- x) z)
        id (str x "/" y "/" z)]
    [:div.hexagon {:id  id}
     [:div.hexagon-inner {:class (when (= [x y z] @state/selected) "selected")}
      (when-let [found (seq (stone/fetch-stone [x y z]))]
        [Stone found])]]))
