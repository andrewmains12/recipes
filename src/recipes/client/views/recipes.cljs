(ns recipes.client.views.recipes
  "Views for displaying recipes"
  (:require [crate.core :as crate]
            [jayq.core :as jq]
            [waltz.state :as state]
            )

  (:use [jayq.core :only [$ append text html]]
        [waltz.state :only [trigger]])    
  (:use-macros [crate.def-macros :only [defpartial]]))


(defpartial ingredient [{:keys [name amount units]}]
  [:div.ingredient
   [:span.name name]
   [:span.num amount]
   [:span.unit units]])

(defpartial recipe-box []
  [:div#recipe-box
   [:h2 Recipe]
   [:div#ingredients]
   [:div#instructions]
   [:div#picture]])

(defpartial loading []
  
  )

(defmulti render
  "Render the recipes based on the current state of the application"
  (fn [state-machine & rest] (state/current state-machine)))

(defmethod render #{:loading}
  [_ ]
  )

(defmethod render #{:normal}
  [_ recipe]  
  )

  
