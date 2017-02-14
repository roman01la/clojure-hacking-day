(ns cheetah.core
  (:require [rum.core :as rum]))

(enable-console-print!)

(rum/defc hello-world []
    [:h1 "Hello world!"])

(rum/mount (hello-world)
           (. js/document (getElementById "app")))
