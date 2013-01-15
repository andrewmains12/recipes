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
      (state/unset me :normal)
      (state/set me :loading)      
      (remote (recipe new-recipe-id) [new-recipe]
              (state/trigger me :loaded new-recipe)))

    (defevent me :loaded [recipe]
      (state/unset me :loading)
      (state/set me :normal recipe)) 
    me))


(declare add-listeners)

(def recipe-index
  (let [me (state/machine "recipe-list")
        render render-recipe-index
        ]
    (defstate me :loading
      (in []
          (render me)))

    (defstate me :unselected
      (in [recipe-list]
          (em/wait-for-load
           (render me recipe-list)
           (add-listeners))
          ))

    (defstate me :selected
      (in [recipe-node]
          (render me recipe-node)))

    (defevent me :select [recipe-node]
      (state/unset me :unselected)
      (state/set me :selected recipe-node)
      (state/trigger recipe-box :change-recipe
                     (int (em/from recipe-node (em/get-attr :value))))

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

(em/defaction add-listeners []  
  ["#recipe-index option"] (em/listen :click
                                      (fn [event]
                                        (state/trigger recipe-index :select
                                                       (.-currentTarget event)         
                                                       ))
                                      ))
