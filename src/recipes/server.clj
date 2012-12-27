(ns recipes.server
  (:require [noir.server :as server]
            [noir.cljs.core :as cljs]

            )
  (:use [one.templates :only [apply-templates]])
  )

(server/load-views-ns 'recipes.views)

(def cljs-options {:advanced {:externs ["externs/jquery.js"]}})


;;TODO: put this in some sort of configuration thingy and turn it off for prod
                                        ;(server/add-middleware 'apply-templates)




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
