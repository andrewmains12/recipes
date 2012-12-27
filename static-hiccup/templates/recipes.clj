(ns templates.recipes
  "Templates for displaying recipes"
  (:use [hiccup.def :only [defelem]])
  )


(defelem ingredient []
  [:div.ingredient
   [:span.name [:ipsum "Chocolate "]]
   [:span.num [:ipsum "500 "]]
   [:span.unit [:ipsum "lbs"]]])


(defelem recipe-box []
  [:div#recipe-box
   [:h2 "Recipe"]
   [:div#ingredients
    [:h3 "Ingredients"]

    [:ipsum
     [:li (ingredient)]
     [:li (ingredient)]
     ]
    ]
   [:div#instructions]
   [:div#picture]])



