(ns designs.core
  (:require [rum.core :as rum]
            [designs.components :as c]))

(enable-console-print!)

(rum/defc Section [name & children]
  [:div
   [:h2 {:style {:border-bottom "4px solid #eee"
                 :padding "8px 0"}}
    name]
   [:div children]])

(rum/defc Designs []
  [:div {:style {:display "flex"
                 :flex-direction "column"
                 :align-items "center"
                 :padding 32}}
   (Section "Chat screen"
     (c/ChatScreen nil {:width 320}))
   (Section "Header"
     (c/Header "Header" {:width 320})
     (c/MenuButton "MenuButton" {:background "#338de9"
                                 :padding 16
                                 :display "inline-block"}))
   (Section "Chat"
     (c/ChatList "Chat list")
     (c/TimeGroup "Timestamp label")
     (c/ChatMessageSent "Chat message (sent)")
     (c/ChatMessageReceived "Chat message (received)")
     (c/Avatar "Avatar")
     (c/ChatMessageBubble "Chat message bubble")
     (c/MessageInput "Message input"))])

(rum/mount (Designs)
           (. js/document (getElementById "app")))
