(ns recipes.views.main
  (:require [recipes.views.common :as common])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]))

(defpage "/" []
         (common/layout
           [:div#content]))
