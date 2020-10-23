(ns claudion.core
  (:require [reagent.core :as r]
            [reagent.dom :as dom]))


(def row-sizes [6 7 8 9 10 11 10 9 8 7 6])

(defn hex-board []
  (map-indexed (fn [idx row-size]
                 [:div {:key   idx
                        :id    (str "hxrow-" idx)
                        :class (str "hxrow hxlength-" row-size)}
                  (map (fn [n] [:li.hexagon {:key   n
                                             :id    (str idx "-" n)}
                                [:div.hexagon-inner]])
                       (range row-size))])
               row-sizes))

(defn content [state]
  [:div {:id "app-container"}
   [:ul.hexagon-grid
    (hex-board)]])

(defn app-state []
  {:text "Claudion"})

(defn ^:export main []
  (let [state (r/atom (app-state))]
    (dom/render [content state]
                (.getElementById js/document "app"))))
