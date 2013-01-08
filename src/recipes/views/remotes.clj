(ns recipes.views.remotes
  (:use [noir.fetch.remotes])
  )

(def recipes 
  {2 {:title "Pad Thai"   
      :ingredients [{:name "Noodles" :num "1" :unit "pkg"}
                    {:name "Thai stuffffff" :num "2" :unit "tbsp"}]
      :instructions ["Do something"
                     "Do another thing"
                     "Profit!"]
      :image "http://www.google.com/url?source=imglanding&ct=img&q=http://blogchef.net/wp-content/uploads/2011/03/cajunchcikenalternate.jpg&sa=X&ei=WhjiUO6xKYGg8gSc04DwBw&ved=0CAkQ8wc&usg=AFQjCNGzi9fB6fs5HiiFAR-A0btSxSD27w"
      }
   5 {:title "Cajun Chicken Noodles"
      :ingredients [{:name "Noodles" :num "1" :unit "pkg"}
                    {:name "Cajun stuff" :num "2" :unit "tbsp"}]
      :instructions ["Boil noodles"
                     "Fry things"
                     "Nom!"]}
   })


(defremote recipe
  "Return recipe with id"
  ;;TODO: implement me
  [id]
  (get recipes id))

        
(defremote recipe-index
  "Return a list of recipes for user"
  [& {:keys [user] :or {user :current}}]
  (map (fn [[id recipe]] [id (:title recipe)]) recipes)
  )

