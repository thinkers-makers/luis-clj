(defproject luis-clj "0.1.0-SNAPSHOT"
  :description "A client library for LUIS - the Microsoft Language Understanding Intelligent Service API."
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.julienxx/clj-slack "0.5.4"]
                 [slack-rtm "0.1.3"]
                 [com.cemerick/url "0.1.1"]
                 [http-kit "2.2.0"]
                 [cheshire "5.6.3"]]
  :profiles {:dev {:dependencies [[http-kit.fake "0.2.2"]]
                   :plugins [[lein-bikeshed "0.3.0"]
                             [lein-cljfmt "0.5.5"]
                             [jonase/eastwood "0.2.3"]
                             [lein-kibit "0.1.2"]
                             [lein-ancient "0.6.10"]]}})
