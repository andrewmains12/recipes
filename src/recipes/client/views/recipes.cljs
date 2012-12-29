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



;; (defn make-list [content]
;;   "Make an html list out of content"
  



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


(def recipe   {
   :title "Pad Thai"
   :ingredients [{:name "Noodles" :num "1" :unit "pkg"}
                 {:name "Thai stuff" :num "2" :unit "tbsp"}]
   :instructions ["Do something"
                  "Do another thing"
                  "Profit!"]
   })

(em/deftemplate recipe-template "/templates/recipes/recipe" ;[:div.recipe]
  [{:keys [title ingredients instructions]}]
  [:h2] (em/content title)
  ["div#ingredients"] (em/append
                       (map #(ingredient-template %) ingredients))

  ["div#instructions"] (em/append
                        (map #(instruction-template %) instructions))
  )
                                                                             
;; (em/defaction render-recipe [recipe]  
;;    (em/append (:title recipe)))

(defn render-recipe [recipe]
  (em/wait-for-load (em/at js/document
                           [:div#recipe-box] (em/append (recipe-template recipe))
                           )))

  ;; (em/at js/document
  ;;     [:div#recipe-box] (em/append (recipe-template recipe))))

;                     (recipe-template {:title "Foo"})))


;;TODO: move this over to the controller
(defmulti render
  "Render the recipes based on the current state of the application"
  (fn [state-machine & rest] (state/current state-machine)))

(defmethod render #{:loading}
  [_ ]
  )

(defmethod render #{:normal}
  [_ recipe]
  (render-recipe (->str-values recipe))
  )


(defmethod render :default
  [sm & rest]
  (js/alert "Unknown state!"))

