(ns recipes.remotes
  (:use [noir.fetch.remotes])
  )

(defremote recipe
  "Return recipe with id"
  ;;TODO: implement me
  [id]
  {:ingredients [{:name "Chocolate" :amount 500 :units "lbs"}
                 {:name "Vanilla" :amount 2 :units "tbsp"}]
   :instructions ["Do something"
                  "Do another thing"
                  "Profit!"]
   })

  

