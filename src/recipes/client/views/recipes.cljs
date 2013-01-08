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


(em/deftemplate recipe-template "/templates/recipes/recipe" 
  [{:keys [title ingredients instructions image]}]
  [:h2] (em/content title)
  [:img] (em/set-attr :src image)
  ["div#ingredients ul"] (em/append
                          (map #(ingredient-template %) ingredients))
                            
  ["div#instructions ul"] (em/append
                           (map #(instruction-template %) instructions))
  )

(em/deftemplate recipe-index "/templates/recipes/index"
  [recipes]
  [:select] (em/chain
             (em/append
              (for [[id name] recipes]
                (crate/html [:option {:value id} name])))
             (em/set-attr :size (count recipes)))
  
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

;; (defmacro sm-render-default [fn-name]
;;   [sm & rest]
;;   (js/alert (str "Unknown state!" (state/current sm))))

(defmethod render-recipe-box :default  
  [& params]
  (js/alert (str "render-recipe-box: Unknown state!" (state/current sm))))
                                
(defmulti render-recipe-index 
  #(state/current %))

(defmethod render-recipe-index #{:unselected}
  [_ recipe-list]
  (em/at js/document
         [:#recipe-index] (em/content (recipe-index (->str-values recipe-list))))
  )




(defmethod render-recipe-index #{:selected}
  []
  )
(defmethod render-recipe-index #{:loading}
  [_]
  
  )

(defmethod render-recipe-index :default
  [& params]
  (js/alert (str "render-recipe-index: Unknown state!" (state/current sm))))

