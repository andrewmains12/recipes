(ns recipes.client.main
  (:require [noir.cljs.client.watcher :as watcher]
            [clojure.browser.repl :as repl]
            [crate.core :as crate]
            [waltz.state :as state]
;            [recipes.client.views.recipes :as recipe-views]
            [recipes.client.controllers.recipes :as recipes-contr]                       
            )
  (:require-macros [enfocus.macros :as em])
  (:use [recipes.client.views.recipes :only [foo recipe]]        
        ))
  

;;************************************************
;; Dev stuff
;;************************************************

(defn dev-start []
  (watcher/init)
  (repl/connect "http://localhost:9000/repl"))
  

;;************************************************
;; Code
;;************************************************


;; (defpartial up-and-running [foo]
;;   [:p.alert (str "Foo is " foo)])


(defn main [& [mode]]
  (if (= mode :dev)
    (dev-start))
  
  ;;TODO: make this better
  (em/at js/document [:#content] (em/append (crate/html [:div#recipe-box])))
  (state/trigger recipes-contr/recipe-box :change-recipe 2)
  )
  
;;TODO: put mode elsewhere
(main :dev)




