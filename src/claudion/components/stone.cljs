(ns claudion.components.stone
  (:require [claudion.state :as state]))

(defn stone-coords [stone] (first (keys stone)))
(defn stone-properties [stone] (first (vals stone)))
(defn fetch-stone [coords] (select-keys @state/stones [coords]))

(defn neighbouring-zones
  "Returns the set of coordinates of each 3-hex zone surrounding `stone`."
  [stone]
  (letfn [(L [[x y z]] [(dec x) (inc y) z])
          (TL [[x y z]] [x (inc y) (dec z)])
          (TR [[x y z]] [(inc x) y (dec z)])
          (R [[x y z]] [(inc x) (dec y) z])
          (BR [[x y z]] [x (dec y) (inc z)])
          (BL [[x y z]] [(dec x) y (inc z)])]
    (let [coords (stone-coords stone)]
      [[(L coords) (TL coords) (TR coords)]
       [(TL coords) (TR coords) (R coords)]
       [(TR coords) (R coords) (BR coords)]
       [(R coords) (BR coords) (BL coords)]
       [(BR coords) (BL coords) (L coords)]
       [(BL coords) (L coords) (TL coords)]])))

(defn- wealth-unlocked? [stone]
  (let [v (:variant (stone-properties stone))]
    (case @state/potions-spent
      0 (= v "tin")
      1 (= v "iron")
      2 (= v "garnet")
      3 (= v "jade")
      4 (= v "aquamarine")
      5 (= v "gold"))))

(defn- free-neighbouring-zone? [stone]
  (let [zones      (neighbouring-zones stone)
        free-zones (reduce (fn [_ zone]
                             (when (every? empty? (map fetch-stone zone))
                               (reduced zone)))
                           []
                           zones)]
    (boolean (seq free-zones))))

(defn free? [stone]
  (if (= "wealth" (:type (stone-properties stone)))
    (and (wealth-unlocked? stone) (free-neighbouring-zone? stone))
    (free-neighbouring-zone? stone)))

(defn- matching-colour? [stone1 stone2]
  (letfn [(wildcard? [s] (= (:variant (stone-properties s)) "wildcard"))]
    (let [{stone1-type    :type
           stone1-variant :variant} (stone-properties stone1)
          {stone2-type    :type
           stone2-variant :variant} (stone-properties stone2)]
    (and (= "colour" stone1-type stone2-type)
         (or (or (wildcard? stone1) (wildcard? stone2))
             (= stone1-variant
                stone2-variant))))))

(defn- opposing-life-stones? [stone1 stone2]
  (let [{stone1-type    :type
         stone1-variant :variant} (stone-properties stone1)
        {stone2-type    :type
         stone2-variant :variant} (stone-properties stone2)]
    (and (= "life" stone1-type stone2-type)
         (not= stone1-variant stone2-variant))))

(defn- potion-and-wealth-stones? [stone1 stone2]
  (let [{stone1-type :type} (stone-properties stone1)
        {stone2-type :type} (stone-properties stone2)]
    (= #{"potion" "wealth"}
       #{stone1-type stone2-type})))

(defn try-remove-stones! [stone1 stone2]
  (let [can-remove? (cond
                      (= stone1 stone2)                         false
                      (not (free? stone2))                      false
                      (matching-colour? stone1 stone2)          true
                      (potion-and-wealth-stones? stone1 stone2) true
                      (opposing-life-stones? stone1 stone2)     true
                      :else                                     false)]
    (when can-remove?
      (when (potion-and-wealth-stones? stone1 stone2) (swap! state/potions-spent inc))
      (swap! state/stones dissoc (stone-coords stone1) (stone-coords stone2))))
  (reset! state/selected nil))

(defn- gold-stone? [stone]
  (= (stone-properties stone)
     {:type "wealth" :variant "gold"}))

(defn select-or-remove-stone! [stone is-free]
  (if (and is-free (gold-stone? stone))
    (swap! state/stones dissoc (stone-coords stone))
    (if-let [pre-selected (seq (fetch-stone @state/selected))]
      (try-remove-stones! pre-selected stone)
      (when is-free (reset! state/selected (stone-coords stone))))))

(defn- stone-img [stone]
  (let [{:keys [type variant]} (stone-properties stone)]
    (case type
      "colour" "images/stone.svg"
      "wealth" (if (= variant "gold") "images/gold.svg" "images/wealth.svg")
      "potion" "images/potion.svg"
      "life"   (if (= variant "pale") "images/pale_dual.svg" "images/vibrant_dual.svg"))))

;; TODO: Would it be worth storing `free?` result on the stone as something that can be accessed
;; by other fns? So it only needs to be worked out when rendering the stone
(defn Stone [stone]
  (let [{:keys [type variant]} (first (vals stone))
        stone-class            (str type "-" variant)
        is-free                (free? stone)]
    [:div.stone {:key   (stone-coords stone)
                 :class (when is-free "free")}
     [:img {:src     (stone-img stone)
            :class   [stone-class (when (not is-free) "locked")]
            :onClick #(select-or-remove-stone! stone is-free)}]]))
