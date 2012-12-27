(ns recipes.client.util
  (:require [clojure.string :as string])
  )


(defn template-url [ns-qualified-name]
  (str "/" (string/replace ns-qualified-name "." "/")))
    
  
  
