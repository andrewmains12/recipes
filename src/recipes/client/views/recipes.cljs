(ns recipes.client.views.recipes
  "Views for displaying recipes"
  (:require [crate.core :as crate]
            [jayq.core :as jq]
            [waltz.state :as state]
            [enfocus.core :as ef]
            )
  (:require-macros [enfocus.macros :as em])
  (:use [jayq.core :only [$ append text html]]
        [waltz.state :only [trigger]])    
  (:use-macros [crate.def-macros :only [defpartial]]))





(defmulti render
  "Render the recipes based on the current state of the application"
  (fn [state-machine & rest] (state/current state-machine)))

(defmethod render #{:loading}
  [_ ]
  )

(defmethod render #{:normal}
  [_ recipe]
  
  )

  
