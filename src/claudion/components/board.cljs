(ns claudion.components.board
  (:require [claudion.components.hex :refer [Hex]]))

(defn- derive-row-lengths [n-of-rows r]
  (let [top-half    (range (inc r) (inc n-of-rows))
        bottom-half (reverse top-half)]
    (concat (drop-last top-half) bottom-half)))

(defn- derive-x-offsets [no-of-rows r]
  (let [neg-sl (- r)]
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

(defn- generate-z-coords [r row-lengths]
  (let [z-indices (range (- r) (inc r))]
    (map (fn [rl z] (repeat rl z))
         row-lengths
         z-indices)))

(defn HexBoard [n-rows]
  (let [r           (Math/floor (/ n-rows 2))
        row-lengths (derive-row-lengths n-rows r)
        x-offsets   (derive-x-offsets n-rows r)
        x-grid      (generate-x-coords row-lengths x-offsets)
        z-grid      (generate-z-coords r row-lengths)]
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
