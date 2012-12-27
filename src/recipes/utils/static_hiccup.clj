(ns recipes.utils.static-hiccup  
  (:require [noir.options :as options]
            [noir.statuses :as statuses]
            [hiccup.core :as hiccup]
            )
  (:use [clojure.string :only [blank? split join]])
  )


(defmulti render-hiccup
  "Render a hiccup resource"
  (fn
    ([namespace-str] (if (options/dev-mode?) :dev :prod))
    ([namespace-str mode] mode)))


(defmethod render-hiccup :dev
  [namespace-str & mode]
  (let [elems (filter #(not (blank? %)) (split namespace-str #"/"))
        ns-qualified-sym (symbol (str (join "." (butlast elems)) "/" (last elems)))
        ]          
    (if-let [var (find-var ns-qualified-sym)]
      (hiccup/html
       (if (fn? var) (@var) @var))
      ;else
      (statuses/get-page 404)  ;render not found       
  )))

(defmethod render-hiccup :default
  [namespace-str & mode]
  (render-hiccup namespace-str :dev))


