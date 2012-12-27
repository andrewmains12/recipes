(ns templates.recipes
  "Templates for displaying recipes"
  (:use [hiccup.def :only [defelem]])
  )


(defelem ingredient []
  [:div.ingredient
   [:span.name "Chocolate "]
   [:span.num "500 "]
   [:span.unit "lbs"]])


(defelem recipe []
  [:h2 "Grandma's awesome thing"])
  ;; [:div#ingredients
  ;;  [:h3 "Ingredients"]
  ;;  ;; [:ipsum
  ;;  ;;  [:li (ingredient)]
  ;;  ;;  [:li (ingredient)]
  ;;  ;;  ]
  ;;  ]
  ;; [:div#instructions]
  ;; [:div#picture])



