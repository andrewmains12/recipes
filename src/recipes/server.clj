(ns recipes.server
  (:require [noir.server :as server]
            [noir.cljs.core :as cljs]

            )
  (:use [one.templates :only [apply-templates]]
        [recipes.utils.static-hiccup :only [wrap-static-reloading]]
        )
  )

(server/load-views-ns 'recipes.views)

(def cljs-options {:advanced {:externs ["externs/jquery.js"]}})


;;TODO: put this in some sort of configuration thingy 
(server/add-middleware wrap-static-reloading)

(defn -main [& m]
  (let [mode (keyword (or (first m) :dev))
        port (Integer. (get (System/getenv) "PORT" "8090"))]
    (cljs/start mode cljs-options)
    (server/start port {:mode mode
                        :ns 'recipes
                        })))

;;Map of options per mode:
;;What middleware?
;;Other things
