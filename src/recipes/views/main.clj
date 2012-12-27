(ns recipes.views.main
  (:require [recipes.views.common :as common])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]
        [recipes.utils.static-hiccup :only [render-hiccup]]
        ))

(defpage "/" []
         (common/layout
          [:div#content
           [:div#recipe-box]
           ]))


(defpage "/templates/*" {namespace-str :*}
  (render-hiccup (str "templates/" namespace-str))) ;;xxx This is kind of hacky--it would be better to match the entire URI
