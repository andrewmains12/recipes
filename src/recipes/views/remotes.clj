(ns recipes.views.remotes
  (:use [noir.fetch.remotes])
  )

(defremote recipe
  "Return recipe with id"
  ;;TODO: implement me
  [id]
  {
   :title "Pad Thai"
   :ingredients [{:name "Noodles" :num 1 :unit "pkg"}
                 {:name "Thai stuff" :num 2 :unit "tbsp"}]
   :instructions ["Do something"
                  "Do another thing"
                  "Profit!"]
   })

  

