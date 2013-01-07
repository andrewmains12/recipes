(ns recipes.views.main
  (:require [recipes.views.common :as common])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]
        [recipes.utils.static-hiccup :only [render-hiccup]]
        ))

(defpage "/" []
         (common/layout
          [:div#content
           ]))
(defpage "/templates/*" {namespace-str :* :as params};{namespace-str :* :as params}
  (apply render-hiccup (cons (str "templates/" namespace-str)
                             (flatten (seq params)))))

;; (defpage "/css/*" {namespace-str :* :as params}
;;   (apply render-hiccup (cons (str "templates/" namespace-str)
;;                              (flatten (seq params)))))
;;xxx This is kind of hacky--it would be better to match the entire URI


