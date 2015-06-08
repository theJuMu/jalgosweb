(ns jalgosweb.db.core
    (:require [monger.core :as mg]
              [monger.collection :as mc]
              [monger.operators :refer :all]
              [monger.conversion :refer [from-db-object]]))


(defonce db (let [uri (get (System/getenv) "MONGOHQ_URL"
                           "mongodb://127.0.0.1/jalgosweb")
                  {:keys [conn db]} (mg/connect-via-uri uri)]
              db))

(defn create-user [user]
  (mc/insert db "users" user))

(defn update-user [id first-name last-name email]
  (mc/update db "users" {:id id}
             {$set {:first_name first-name
                    :last_name last-name
                    :email email}}))
(defn get-user [id]
  (mc/find-one-as-map db "users" {:id id}))
