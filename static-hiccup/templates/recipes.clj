(ns templates.recipes
  "Templates for displaying recipes"
  (:use [hiccup.def :only [defelem]])
  )


(defelem ingredient []
  [:div.ingredient
   [:span.name "Chocolate "]
   [:span.num "500 "]
   [:span.unit "lbs"]])


(defelem recipe-box []
  [:div#recipe-box
   [:h2 "Recipe"]
   [:div#ingredients]
   [:h3 "Ingredients"]
   (ingredient)
   (ingredient)
   [:div#instructions]
   [:div#picture]])



