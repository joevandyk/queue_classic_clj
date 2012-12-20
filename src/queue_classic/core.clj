(ns queue-classic.core 
  (require [clojure.java.jdbc :as sql])
  (require [net.cgrand.enlive-html :as h])
  (:gen-class)
)


(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(queue-classic.core/foo "yup")


(definline db [body] `(sql/with-connection (System/getenv "DATABASE_URL") ~body))
(defn query [sql] (sql/with-query-results results [sql] (into [] results)))
(defn pluck [k sql] (db(k (first (query sql)))))

;(prn (pluck :username "select * from users offset 1 limit 1"))

(h/sniptest "<h1>Lorem Ipsum</h1>")

(defn template [] "
  <div class='body'>
    <h1>Sample Page Title</h1>
    <ul>
      <li>links</li>
    </ul>
  </div>")

(defn dudes [] ["joe" "rocks" "here" "5"])

(println "before test")
(println (h/sniptest (template)
  [:h1] (h/content "Hello Reader!")
  [:ul :li] (h/clone-for [i (dudes)] (h/content i)) 
))
(println "after test")

(defn -main [& args]
  (foo "yup: ")
)


(println "sup" "dude")
