(ns recipes.client.controllers.recipes
  "Controller for recipes"
  (:require [fetch.remotes :as remotes]
            [waltz.state :as state])

  (:require-macros [fetch.macros :as fm]
                   [enfocus.macros :as em]
                   )
  (:use-macros [waltz.macros :only [in out defstate defevent]]
               [fetch.macros :only [letrem remote]]
               )
  (:use [recipes.client.views.recipes :only [render-recipe-box render-recipe-index]]))
  
  
;;TODO: DRY state machine definitions up with a macro  
(def recipe-box
  (let [me (state/machine "recipe-box")
        render render-recipe-box
        ]
    ;;States
    (defstate me :normal
      (in [recipe]
          (render me recipe)))
          
    (defstate me :loading
      (in [] (render me)))
    
    ;;Events
    (defevent me :change-recipe [new-recipe-id]
      (state/set me :loading)      
      (remote (recipe new-recipe-id) [new-recipe]
              (state/trigger me :loaded new-recipe)))

    (defevent me :loaded [recipe]
      (state/unset me :loading)
      (state/set me :normal recipe)) 
    me))


;; (defn add-listeners []
;;   (em/at js/document
;;          ["#recipe-index option"] (em/listen :click
;;                                              (fn [node]
;;                                                (js/alert (.-currentTarget node)))
;;                                              )))

(def recipe-index
  (let [me (state/machine "recipe-list")
        render render-recipe-index
        ]
    (defstate me :loading
      (in []
          (render me)))

    (defstate me :unselected
      (in [recipe-list]
          (render me recipe-list)
          ;; (add-listeners)
          ))

    (defstate me :selected
      (in [recipe-id]
          (render me recipe-id)))

    (defevent me :select [recipe-id]
      (state/unset me :unselected)
      (state/set me :selected recipe-id)
      (state/trigger recipe-box
                     :change-recipe recipe-id)
      )


    (defevent me :refresh-recipes [] ;;Possibly take category here
      (state/unset me :selected)
      (state/set me :loading)
      (letrem [recipe-list (recipe-index)]
              (state/trigger me :loaded recipe-list))
      )

    (defevent me :loaded [recipe-list]
      (state/unset me :loading)
      (state/set me :unselected recipe-list)
      )
    me))
