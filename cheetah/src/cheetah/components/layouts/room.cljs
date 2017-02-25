(ns cheetah.components.layouts.room
  (:require [rum.core :as rum]
            [cheetah.api :refer [send!]]
            [cheetah.components.base :as base]))

(defn send-msg! [msg]
  (-> (send! msg)
      (.then #(console.log "MSG SENT" %))
      (.catch #(console.log "MSG SEND ERR" %))))

(defn create-msg [uname room text]
  {:uname uname
   :room room
   :text text})

(rum/defc ChatMsg [{:keys [uname text avatar]} owner-name]
  [:li {:class (str "chat-msg" (when (= uname owner-name) " owner"))}
   (base/Avatar {:url avatar})
   [:div.bubble
    [:div {:class (if (= uname owner-name) "bubble-uname_owner" "bubble-uname")}
     uname]
    [:div.body text]]])

(rum/defcs Layout <
  rum/reactive
  (rum/local "" ::val)
  [{val ::val} db {:keys [id]}]
  (let [uname (get-in @db [:user :name])
        msgs (rum/react (rum/cursor-in db [:rooms id]))]
    [:div.screen.chat
     (base/Header {:title id
                   :back? true
                   :users? (count (keys (group-by :uname msgs)))})
     [:main
      [:ul.chat-list
       (for [msg msgs]
        (ChatMsg msg uname))]]
     [:footer.app-footer
      [:form.message
       {:on-submit #(do
                     (.preventDefault %)
                     (send-msg! (create-msg uname id @val)))}
       (base/Input {:class "message"
                    :value @val
                    :on-change #(reset! val (.. % -target -value))})
       (base/Button {:disabled (empty? @val)}
                    "Send")]]]))
