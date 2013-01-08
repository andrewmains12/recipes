(ns templates.recipes
  "Templates for displaying recipes"
  (:use [hiccup.def :only [defelem]])
  )




(defelem ingredient [& {:keys [name num unit description]}]
  [:li.ingredient
   [:span.num [:ipsum num]]
   [:span.unit [:ipsum unit]]   
   [:span.name [:ipsum name]]
   [:span.description [:ipsum description]]
   ])


(def ipsum-img "http://www.google.com/url?source=imglanding&ct=img&q=http://blogchef.net/wp-content/uploads/2011/03/cajunchcikenalternate.jpg&sa=X&ei=WhjiUO6xKYGg8gSc04DwBw&ved=0CAkQ8wc&usg=AFQjCNGzi9fB6fs5HiiFAR-A0btSxSD27w")




(defelem picture []
  [:img {:src ipsum-img}]
  )

(defelem instruction []
  [:li.instruction [:ipsum "Do things"]])

(defelem recipe []
  [:div.recipe
   [:h2 "Cajun Chicken Pasta"]
   [:div#ingredients
    [:h3 "Ingredients"]
    [:ul
     [:ipsum 
      (ingredient :name "Linguine"
                  :num 4
                  :unit "oz"
                  :description "Cooked al dente")
      (ingredient :name "Cajun Seasoning"
                  :num 2
                  :unit "tsp")
      ]
     ]
    ]
   [:div#instructions
    [:h3 "Instructions"]
    [:ul
     [:ipsum
      (instruction)
      (instruction)
      ]
     ]]
   [:div#picture
    [:img]
    [:ipsum
     (picture)]
    ]
    ])



(defelem recipe-box []
  [:div#recipe-box
   [:ipsum (recipe)]])

(defelem index []
   [:select])
   
