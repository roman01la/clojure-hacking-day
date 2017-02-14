(defproject cheetah "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :min-lein-version "2.7.1"

  :dependencies [[org.clojure/clojure "1.9.0-alpha14"]
                 [org.clojure/clojurescript "1.9.473"]
                 [rum "0.10.8"]]

  :plugins [[lein-figwheel "0.5.9"]
            [lein-cljsbuild "1.1.5" :exclusions [[org.clojure/clojure]]]]

  :source-paths ["src"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]

  :cljsbuild {:builds
              [{:id "dev"
                :source-paths ["src"]
                :figwheel true
                :compiler {:main cheetah.core
                           :asset-path "js/compiled/out"
                           :output-to "resources/public/js/compiled/cheetah.js"
                           :output-dir "resources/public/js/compiled/out"
                           :source-map-timestamp true
                           :preloads [devtools.preload]}}

               {:id "min"
                :source-paths ["src"]
                :compiler {:output-to "resources/public/js/compiled/cheetah.js"
                           :main cheetah.core
                           :optimizations :advanced
                           :pretty-print false}}

               {:id "designs"
                 :source-paths ["src"]
                 :figwheel true
                 :compiler {:main designs.core
                            :asset-path "js/compiled/designs"
                            :output-to "resources/public/js/compiled/designs.js"
                            :output-dir "resources/public/js/compiled/designs"
                            :source-map-timestamp true
                            :preloads [devtools.preload]}}]}

  :figwheel {:server-port 3000
             :css-dirs ["resources/public/css"]}

  :profiles {:dev {:dependencies [[binaryage/devtools "0.9.0"]
                                  [figwheel-sidecar "0.5.9"]
                                  [com.cemerick/piggieback "0.2.1"]]
                   :source-paths ["src" "dev"]}})
