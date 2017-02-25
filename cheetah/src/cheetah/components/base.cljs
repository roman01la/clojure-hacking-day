(ns cheetah.components.base
  (:require [rum.core :as rum]))

(rum/defc Input [props]
  [:input props])

(rum/defc Button [props label]
  [:button props label])

(rum/defc Header [{:keys [title back? users?]}]
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
    [:button.menu-btn "☰"]]])

(rum/defc Avatar [{:keys [url size]}]
  [:img.avatar
   {:src url
    :width size
    :height size}])
