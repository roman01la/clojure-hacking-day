(ns cheetah.components.layouts.room
  (:require [rum.core :as rum]
            [goog.object :as gobj]
            [cheetah.api :refer [send!]]
            [cheetah.components.base :as base]))

(set! *warn-on-infer* true)

(defn send-msg! [msg cb]
  (-> ^js/Promise (send! msg)
      ^js/Promise (.then cb)))

(defn create-msg [uname room text avatar]
  {:uname uname
   :room room
   :text text
   :avatar avatar
   :time (js/Date.now)})

(rum/defc ChatMsg [{:keys [uname text avatar time]} owner-name]
  [:li {:class (str "chat-msg" (when (= uname owner-name) " owner"))}
   [:div.time (-> time js/Date. .toLocaleString)]
   [:div
    (base/Avatar {:url avatar})
    [:div.bubble
     [:div {:class (if (= uname owner-name) "bubble-uname_owner" "bubble-uname")}
      uname]
     [:div.body text]]]])

(rum/defcs Layout <
  rum/reactive
  (rum/local "" ::val)
  [{val ::val} db history {:keys [id]}]
  (let [uname (-> @db :user :name)
        avatar (-> @db :user :avatar)
        msgs (->> (rum/cursor-in db [:messages]) rum/react (group-by :room))
        msgs (sort-by :time (get msgs id))]
    [:div.screen.chat
     (base/Header db
                  {:title id
                   :back? true})
     [:main
      [:ul.chat-list
       (for [msg msgs]
        (ChatMsg msg uname))]]
     [:footer.app-footer
      [:form.message
       {:on-submit #(do
                     (.preventDefault ^js/Event %)
                     (send-msg! (create-msg uname id @val avatar)
                                (partial reset! val "")))}
       (base/Input {:class "message"
                    :value @val
                    :on-change #(reset! val (-> % (gobj/get "target") (gobj/get "value")))})
       (base/Button {:disabled (empty? @val)}
                    "Send")]]
     (base/Menu db history)]))
