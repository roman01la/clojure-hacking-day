(ns cheetah.components.layouts.sign-in
  (:require [rum.core :as rum]
            [pushy.core :as pushy]
            [cheetah.api :refer [auth!]]
            [cheetah.components.base :as base]))

(defn handle-auth [email db history]
  (-> (auth! email email)
      (.then #(swap! db assoc-in [:user :name] email))
      (.then #(pushy/set-token! history "/rooms"))))

(rum/defcs Layout <
  (rum/local "" ::uname)
  [{uname ::uname} db history]
  [:div.screen.signin
   [:h1 [:em "Cheetah"]]
   [:form.signin {:on-submit #(do
                               (.preventDefault %)
                               (handle-auth @uname db history))}
    (base/Input {:placeholder "Username"
                 :value @uname
                 :on-change #(reset! uname (.. % -target -value))})
    (base/Button {} "Sign In")]])
