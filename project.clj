(defproject recipes "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://exampl.com/FIXME"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [noir-cljs "0.3.7"]
                 [enlive "1.0.1"]
                 [noir "1.3.0"]                 

                 ;;cljs
                 [jayq "1.0.0"]
                 [fetch "0.1.0-alpha2" :exclusions [org.clojure/clojure]]
                 [waltz "0.1.0-alpha1"]
                 [crate "0.2.3"]
                 [enfocus "1.0.0-beta2"]
                 [one "1.0.0-SNAPSHOT"]
                 ]
  :cljsbuild {:builds [{}]}
  :source-paths ["src" "static-hiccup"]
  :main ^{:skip-aot true} recipes.server
  )

