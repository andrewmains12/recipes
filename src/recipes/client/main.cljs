(ns recipes.client.main
  (:require [noir.cljs.client.watcher :as watcher]
            [clojure.browser.repl :as repl]
            [crate.core :as crate]
            [recipes.client.views.recipes :as recipe-views]
            )
  (:use [jayq.core :only [$ append]]
        [waltz.state :only [trigger]])
  (:use-macros [crate.def-macros :only [defpartial]]))

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
(defpartial up-and-running [foo]
  [:p.alert (str "Foo is " foo)])


(defn main [& [mode]]
  (if (= mode :dev)
    (dev-start))

  (append $content (up-and-running 2)))
  

;;TODO: put mode elsewhere
(main :dev)




