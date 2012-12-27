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


(em/deftemplate ingredient-template "/templates/recipes/ingredient" [{:keys [name num unit]}]
  [:span.name] (em/content name)
  [:span.num] (em/content num)
  [:span.unit] (em/content unit)
  )


(em/deftemplate recipe-template "/templates/recipes/recipe" [{:keys [title ingredients instructions]}]
  [:h2] (em/content title)
  ;; [":div#ingredients :h3"] (em/after
  ;;                         (em/clone-for [ingr ingredients]
  ;;                                       (ingredient-template ingr)))
  )


;; (em/defaction render-recipe [recipe]  
;;    (em/append (:title recipe)))
(defn render-recipe [recipe]
  (em/at js/document
         [:div#recipe-box] (em/append (recipe-template recipe))))

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
  (em/wait-for-load (render-recipe recipe))
  )




