(ns queue-classic.core)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))


(queue-classic.core/foo "yup")

(require '[clojure.java.jdbc :as sql])

(definline db [body] `(sql/with-connection "postgres://localhost/tanga_dev" ~body))
(defn query [sql] (sql/with-query-results results [sql] (into [] results)))
(defn pluck [k sql] (db(k (first (query sql)))))

(pluck :username "select * from users offset 1 limit 1")
