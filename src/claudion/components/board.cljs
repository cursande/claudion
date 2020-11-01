(ns claudion.components.board
  (:require [claudion.components.hex :refer [Hex]]))

(defn- derive-row-lengths [n-of-rows side-length]
  (let [top-half    (range (inc side-length) (inc n-of-rows))
        bottom-half (reverse top-half)]
    (concat (drop-last top-half) bottom-half)))

(defn- derive-x-offsets [no-of-rows side-length]
  (let [neg-sl (- side-length)]
    (for [n (range no-of-rows)]
      (let [neg-n (- n)]
        (if (< neg-n neg-sl)
          neg-sl
          neg-n)))))

(defn- generate-x-coords [row-lengths x-offsets]
  (map (fn [row-length x-offset]
         (take row-length (iterate inc x-offset)))
       row-lengths
       x-offsets))

(defn- generate-z-coords [side-length row-lengths]
  (let [z-indices (range (- side-length) (inc side-length))]
    (map (fn [rl z] (repeat rl z))
         row-lengths
         z-indices)))

(defn HexBoard [n-rows]
  (let [side-length (Math/floor (/ n-rows 2))
        row-lengths (derive-row-lengths n-rows side-length)
        x-offsets   (derive-x-offsets n-rows side-length)
        x-grid      (generate-x-coords row-lengths x-offsets)
        z-grid      (generate-z-coords side-length row-lengths)]
    [:div.hexagon-grid
     (map (fn [idx x-row z-row]
            [:div {:key   idx
                   :id    (str "hxrow-" idx)
                   :class "hxrow"}
             (map (fn [x z] [Hex x z])
                  x-row
                  z-row)])
          (range n-rows)
          x-grid
          z-grid)]))
