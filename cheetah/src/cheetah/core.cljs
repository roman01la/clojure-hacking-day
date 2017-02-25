(ns cheetah.core
  (:require [rum.core :as rum]
            [bide.core :as r]
            [pushy.core :as pushy]
            [cheetah.api :refer [subscribe!]]
            [cheetah.db :refer [db]]
            [cheetah.components.layout :refer [Layout]]
            [cheetah.components.layouts.sign-in :as sign-in]
            [cheetah.components.layouts.chat-list :as chat-list]
            [cheetah.components.layouts.room :as room]))

(enable-console-print!)

(subscribe! #(swap! db assoc :rooms (group-by :room %)))


(def router
  (r/router [["/" :sign-in]
             ["/rooms" :chat-list]
             ["/rooms/:id" :room]]))

(def history
  (pushy/pushy #(swap! db assoc :route %) (partial r/match router)))

(pushy/start! history)

(rum/defc Router < rum/reactive [db history]
  (let [[handler params] (rum/react (rum/cursor-in db [:route]))]
    (Layout
     (case handler
           :sign-in (sign-in/Layout db history)
           :chat-list (chat-list/Layout db params)
           :room (room/Layout db params)
           nil))))

(defn render []
  (rum/mount (Router db history)
             (. js/document (getElementById "app"))))

(render)
