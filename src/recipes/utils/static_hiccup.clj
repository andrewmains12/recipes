(ns recipes.utils.static-hiccup  
  (:require [noir.options :as options]
            [noir.statuses :as statuses]
            [hiccup.core :as hiccup]
            [recipes.views.common :as common]
            [net.cgrand.enlive-html :as enlive])
            
  (:use [clojure.string :only [blank? split join]]
        [ring.middleware.reload :only [wrap-reload]])
  )

;;xxx TODO: config this
(def ^:dynamic *hiccup-dir* "Directory to search for code containing static hiccup vectors in"
  "static-hiccup")


(defn hiccup->bare-html [hiccup-vec]
  ;;xxx HACK. We need to select the tag root from the enlive nodes, since enlive helpfully adds all kinds of nodes we don't 
  ;;want
  (let [tag-root (first hiccup-vec)]
    (-> hiccup-vec
        (hiccup/html ,,,)
        (with-in-str ,,, (enlive/html-resource *in*))
        (enlive/at ,,, [:ipsum] nil)
        (enlive/select ,,, [tag-root])
        (#(apply str (enlive/emit* %)) ,,,))))
      ;render
;; (#(apply str  %) ,,,))
;  )
      

(defn wrap-static-reloading [handler]
  "Reload the static hiccup folder"
  (if (options/dev-mode?)
    (wrap-reload handler {:dirs [*hiccup-dir*]})
        handler))

(defmulti render-hiccup
  "Render a hiccup resource"
  (fn [namespace-str & {:keys [mode] :or {mode :dev}}]
    mode))



        
(defmethod render-hiccup :dev
  [namespace-str & {:keys [design] :as options}]
  (let [elems (filter #(not (blank? %)) (split namespace-str #"/"))  
        ns-sym (symbol (join "." (butlast elems)))
        ns-qualified-sym (symbol (str ns-sym "/" (last elems)))
        ]
    (when (nil? (find-ns ns-sym))
      (require ns-sym))
    (if-let [var (find-var ns-qualified-sym)]
      (let [val (if (fn? @var) (@var) @var)
            rtn-html (if-not design (hiccup->bare-html val) (common/head val))
            ]
        rtn-html)
      (statuses/get-page 404)  ;render not found
  )))



;; (defmethod render-hiccup :default
;;   [namespace-str & mode]
;;   (render-hiccup namespace-str :dev))


