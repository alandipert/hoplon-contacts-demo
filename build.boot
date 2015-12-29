(set-env!
 :dependencies '[[adzerk/boot-cljs          "1.7.170-3"]
                 [adzerk/boot-reload        "0.4.2"]
                 [org.clojure/clojurescript "1.7.170"]
                 [org.clojure/clojure       "1.7.0"]
                 [hoplon/boot-hoplon        "0.1.13"]
                 [hoplon                    "6.0.0-alpha10"]
                 [alandipert/storage-atom   "1.2.4"]
                 [tailrecursion/boot-jetty  "0.1.3"]]
 :resource-paths #{"assets"}
 :source-paths #{"src"})

(require
 '[adzerk.boot-cljs   :refer [cljs]]
 '[adzerk.boot-reload :refer [reload]]
 '[hoplon.boot-hoplon :refer [hoplon prerender]]
 '[tailrecursion.boot-jetty :refer [serve]])

(deftask dev
  "Build for local development."
  []
  (comp
   (watch)
   (speak :theme "pillsbury")
   (hoplon)
   (reload)
   (cljs)
   (serve :port 8000)))

(deftask prod
  "Build for production deployment."
  []
  (comp
   (hoplon)
   (cljs :optimizations :advanced)
   (prerender)
   (target :dir #{"target"})))
