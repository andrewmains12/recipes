(ns css.recipes
  (:require [gaka.core :as gaka]))

(def rules
  
  [:div#recipe-box
   :width "50%"
   :margin "0px auto"
   :border "2px solid"
  
   ["div#ingredients ul"
    :display "table"
;   :border-spacing "1%"
   
    [:.ingredient
     :display "table-row"
     [:span
      :display "table-cell"]

     [:span.name
      :padding-right "100px"
      ]
    [:span.num
     :padding-right "10px"]
    
   ;; [:.name  :margin-right "8px"]
   ;; [:.num  :margin-right "8px"]

   ]]])
    


;(gaka/save-css "resources/public/css/recipes.css" rules)

  
  
        
