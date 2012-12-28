(ns recipes.client.controllers.recipes
  "Controller for recipes"
  (:require [fetch.remotes :as remotes]
            [waltz.state :as state])

  (:require-macros [fetch.macros :as fm])
  (:use-macros [waltz.macros :only [in out defstate defevent]]
               [fetch.macros :only [letrem remote]]
               )
  (:use [recipes.client.views.recipes :only [render]])
  )
  
  
(def sm
  (let [me (state/machine "recipes")]
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


