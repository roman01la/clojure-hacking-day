(ns cheetah.components.base
  (:require [rum.core :as rum]
            [cheetah.api :as api]
            [pushy.core :as pushy]
            [cheetah.db :refer [initial-state]]))

(set! *warn-on-infer* true)

(rum/defc Input [props]
  [:input props])

(rum/defc Button [props label]
  [:button props label])

(rum/defc Header < rum/static [db {:keys [title back? users?]}]
  [:header.app-header
    [:div.left
     (when back?
      [:button.btn.back {:on-click #(.back js/history)}
       "<"])
     [:div.screen-title
      [:span title]
      (when users?
       [:div.room-users-count (str "Online:" "3 / " users?)])]]
   [:div.right
    [:button.menu-btn {:on-click #(swap! db assoc-in [:menu :visible?] true)}
     "☰"]]])

(rum/defc Avatar [{:keys [url size]}]
  [:img.avatar
   {:src url
    :width size
    :height size}])

(rum/defc Menu <
  rum/static
  rum/reactive
  [db history]
  (let [{:keys [visible?]} (rum/react (rum/cursor-in db [:menu]))
        hide-menu #(swap! db assoc-in [:menu :visible?] false)]
    (when visible?
      [:div.menu-wrap
       [:div.menu
        [:ul.list
         [:li.list-item
          [:a {:href "/rooms" :on-click hide-menu} "Chat Rooms"]
          [:a {:href "/"
               :on-click (fn []
                           (-> ^js/Promise (api/sign-out!)
                            (.then #(pushy/set-token! history "/"))
                            (.then #(reset! db initial-state))))}
              "Sign Out"]]]]
       [:div.overlay {:on-click hide-menu}]])))
