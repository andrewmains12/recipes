(defproject recipes "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://exampl.com/FIXME"   
  :plugins [[lein-cljsbuild "0.2.9"]
            [lein-git-deps "0.0.1-SNAPSHOT"]
            [speclj "2.5.0"]
            ]
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [noir-cljs "0.3.7"]
                 [enlive "1.0.1"]
                 [noir "1.3.0"]                 
                 [gaka "0.3.0"]

                 [speclj "2.5.0"]                 
                 ;;cljs
                 [fetch "0.1.0-alpha2" :exclusions [org.clojure/clojure]]
                 [waltz "0.1.0-alpha1"]
                 [crate "0.2.3"]
                 [enfocus "1.0.0-beta2"]
                 ]

  :git-dependencies [["https://github.com/clojure/clojurescript.git"
                      "88b36c177ebd1bb49dbd874a9d13652fd1de4027"
                      ]]
  :cljsbuild {:builds {:dev {}}}

  :test-paths ["spec/"]
                  
  :source-paths ["src" "static-hiccup"
                 ;;Latest cljs release
                 ".lein-git-deps/clojurescript/src/clj"
                 ".lein-git-deps/clojurescript/src/cljs"
                 ]
  :main ^{:skip-aot true} recipes.server
  )

