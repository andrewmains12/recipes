(ns recipes.views.common
  (:require [noir.cljs.core :as cljs])
  (:use [noir.core :only [defpartial]]
        [hiccup.page :only [include-css html5]]))

(defpartial head [& body]
  (html5
   [:head
    [:title "recipes"]
    (include-css "/css/reset.css")
    (include-css "/css/default.css")]
   body))

(defpartial layout [& content]
  (head
   [:body
    [:div#wrapper
     content]
    (cljs/include-scripts :with-jquery)]))


  
