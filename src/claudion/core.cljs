(ns claudion.core
  (:require [claudion.components.board :refer [HexBoard]]
            [reagent.dom :as dom]))

(def n-of-rows "number of rows for hex grid (must be odd)" 11)

(defn app []
  [:div {:id "app-container"}
   [HexBoard n-of-rows]])

(defn ^:export main []
  (dom/render [app] (.getElementById js/document "app")))
