(set-env!
 :dependencies '[[adzerk/boot-cljs          "0.0-3308-0"]
                 [adzerk/boot-reload        "0.3.0"]
                 [org.clojure/clojurescript "0.0-3308"]
                 [org.clojure/clojure       "1.7.0"]
                 [tailrecursion/boot-hoplon "0.1.0"]
                 [tailrecursion/hoplon      "6.0.0-alpha2"]
                 [alandipert/storage-atom   "1.2.4"]]
 :resource-paths #{"assets"}
 :source-paths #{"src"})

(require
 '[adzerk.boot-cljs          :refer [cljs]]
 '[adzerk.boot-reload        :refer [reload]]
 '[tailrecursion.boot-hoplon :refer [hoplon prerender]])

(deftask dev
  "Build for local development."
  []
  (comp
   (watch)
   (speak)
   (hoplon)
   (reload)
   (cljs)))

(deftask prod
  "Build for production deployment."
  []
  (comp
   (hoplon)
   (cljs :optimizations :advanced)
   (prerender)))
