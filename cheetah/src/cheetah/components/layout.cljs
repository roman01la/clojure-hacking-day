(ns cheetah.components.layout
  (:require [rum.core :as rum]))

(rum/defc Layout [screen]
  [:div
   screen])
