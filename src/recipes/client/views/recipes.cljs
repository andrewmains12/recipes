(ns recipes.client.views.recipes
  "Views for displaying recipes"
  (:require [crate.core :as crate]
            [jayq.core :as jq]
            [waltz.state :as state]
            [enfocus.core :as ef]
            )
  (:require-macros [enfocus.macros :as em])
  (:use [jayq.core :only [$ append text html]]
        [waltz.state :only [trigger]]
;        [recipes.client.controllers.recipes :only [sm]]
        )    
  (:use-macros [crate.def-macros :only [defpartial]]))

;;xxx TODO: get some better solution than this
(defn ->str-values
  [item]
    (cond
     (map? item) (zipmap (keys item) (map ->str-values (vals item)))
     (coll? item) (map ->str-values item)
     :else (str item)))

(em/deftemplate ingredient-template "/templates/recipes/ingredient"
  [{:keys [name num unit]}]
  ["span.name"] (em/content name)
  ["span.num"] (em/content num)
  ["span.unit"] (em/content unit)
  )

(em/deftemplate instruction-template "/templates/recipes/instruction"
  [instruction]
  ["li.instruction"] (em/content instruction)
  )


(em/deftemplate recipe-template "/templates/recipes/recipe" ;[:div.recipe]
  [{:keys [title ingredients instructions image]}]
  [:h2] (em/content title)
  [:img] (em/set-attr :src image)
  ["div#ingredients ul"] (em/append
                          (map #(ingredient-template %) ingredients))
                            
  ["div#instructions ul"] (em/append
                           (map #(instruction-template %) instructions))
  )

(em/deftemplate recipes-index "/templates/recipes/index"
  [recipes]
)  
;; (em/defaction render-recipe [recipe]  
;;    (em/append (:title recipe)))

;; (defn render-recipe [recipe]
;;   )

;;TODO: move this over to the controller



(defmulti render-recipe-box
  "Render the recipes based on the current state of the application"
  #(state/current %)
  )

(defmethod render-recipe-box #{:loading}
  [_ ]
  )

(defmethod render-recipe-box #{:normal}
  [_ recipe]
  (em/wait-for-load (em/at js/document
                           [:div#recipe-box] (em/content
                                              (recipe-template (->str-values recipe)))
                           )))

(defmethod render-recipe-box :default
  [sm & rest]
  (js/alert (str "Unknown state!" (state/current sm))))


;; (defrender "recipe-index"
;;   (render-state #{:normal} [recipe]
                                
(defmulti render-recipe-index 
  #(state/current %))

(defmethod render-recipe-index #{:unselected}
  [_ recipes]
  (em/wait-for-load (em/at js/document
                           [:div#recipes-index] (em/content
                                                 (recipe-index (->str-values recipes))
                                                 )
                           )))
  
