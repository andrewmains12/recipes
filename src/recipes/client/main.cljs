(ns recipes.client.main
  (:require [noir.cljs.client.watcher :as watcher]
            [clojure.browser.repl :as repl]
            [crate.core :as crate]
            [waltz.state :as state]            
;            [recipes.client.views.recipes :as recipe-views]
            [recipes.client.controllers.recipes :as recipes-contr]
            
            )
  (:use [jayq.core :only [$ append]]
        [recipes.client.views.recipes :only [foo recipe]]
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

(def $content ($ :#content))
;; (defpartial up-and-running [foo]
;;   [:p.alert (str "Foo is " foo)])


(defn main [& [mode]]
  (if (= mode :dev)
    (dev-start))
                                        ;  (em/at (ef/css-select [:#content]) (em/append
                                        ;  (foo)
;  (state/trigger recipes-contr/sm :loaded recipe)
  (state/trigger recipes-contr/sm :change-recipe 2)
  )
  

;;TODO: put mode elsewhere
(main :dev)




