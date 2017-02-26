(ns cheetah.components.layouts.sign-in
  (:require [rum.core :as rum]
            [pushy.core :as pushy]
            [cheetah.api :as api]
            [cheetah.components.base :as base]))

(set! *warn-on-infer* true)

(defn- format-time [msgs]
  (->> msgs
    (map #(update % :time
                  (fn [t]
                    (let [d (js/Date. t)
                          nt (js/Date.)]
                      (.setTime nt (+ (.getTime d) (* 60000 (.getTimezoneOffset d))))
                      (.getTime nt)))))))

(defn handle-auth [db history]
  (-> ^js/Promise (api/auth-twitter!)
      ^js/Promise (.then #(swap! db assoc :user {:name (:displayName %)
                                                 :avatar (:photoURL %)}))
      ^js/Promise (.then (fn []
                           (api/subscribe! "messages" #(swap! db assoc :messages (format-time %)))
                           (api/subscribe! "rooms" #(swap! db assoc :rooms %))))
      ^js/Promise (.then #(pushy/set-token! history "/rooms"))))

(rum/defc Layout [db history]
  [:div.screen.signin
   [:h1 [:em "Cheetah"]]
   [:form.signin {:on-submit #(do
                               (.preventDefault ^js/Event %)
                               (handle-auth db history))}
    (base/Button {} "Sign In with Twitter")]])
