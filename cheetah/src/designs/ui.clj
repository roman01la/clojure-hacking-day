(ns designs.ui
  (:require [clojure.string :as s])) ;

(defmacro defui [name html]
  `(rum/defc ~name [name# props#]
    [:div {:style {:width "80vw"
                   :border-bottom "2px solid #eee"
                   :margin "0 0 32px"
                   :padding "0 0 16px"}}
     [:h3 name#]
     [:div {:style props#
            :dangerouslySetInnerHTML (clojure.core/clj->js {:__html ~html})}]
     [:pre {:style {:margin "16px 0 0"}}
      ~html]]))
