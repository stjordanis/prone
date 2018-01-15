(defproject prone "1.1.4"
  :description "Better exception reporting middleware for Ring."
  :url "http://github.com/magnars/prone"
  :license {:name "BSD-3-Clause"
            :url "http://opensource.org/licenses/BSD-3-Clause"}
  :dependencies []
  :filespecs [{:type :fn
               :fn (fn [_]
                     {:type :bytes :path "prone/generated/prone.js"
                      :bytes (slurp "dev-resources/prone/generated/prone.js")})}]
  :profiles {:dev {:dependencies [[org.clojure/clojure "1.8.0"]
                                  [flare "0.2.2"]
                                  [ring "1.2.1"]
                                  [hiccup-find  "0.4.0"]
                                  [org.clojure/clojurescript "0.0-3308"]
                                  [prismatic/schema "0.2.1"]
                                  [quiescent "0.3.2"]
                                  [org.clojure/core.async "0.2.371"]]
                   :injections [(require 'flare.clojure-test)
                                (flare.clojure-test/install!)]
                   :source-paths ["dev"]
                   :prep-tasks [["shell" "./build-js-sources.sh"]]
                   :ring {:handler prone.demo/app}
                   :plugins [[lein-ring "0.8.10"]
                             [lein-cljsbuild "1.0.3"]
                             [lein-shell "0.3.0"]
                             [com.jakemccrary/lein-test-refresh "0.5.5"]]
                   :cljsbuild {:builds [{:source-paths ["src" "dev"]
                                         :compiler {:output-to "dev-resources/prone/generated/prone.js"
                                                    :output-dir "dev-resources/prone/generated/out"
                                                    :optimizations :whitespace}}]}}
             :1.7 {:dependencies [[org.clojure/clojure "1.7.0"]]
                   :test-paths ["test" "test-1.7"]}})
