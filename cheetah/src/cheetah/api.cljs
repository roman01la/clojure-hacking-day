(ns cheetah.api
  (:require [cljsjs.firebase]))

(def fb js/firebase)

(def config
  {:apiKey "AIzaSyDCLdiimM3GemnQ6vB2j3esl23nWwSKRvc"
   :authDomain "cheetah-3deed.firebaseapp.com"
   :databaseURL "https://cheetah-3deed.firebaseio.com"
   :storageBucket "cheetah-3deed.appspot.com"
   :messagingSenderId "1057267043259"})

(defonce api (.initializeApp fb (clj->js config)))

(defn auth! [email pwd]
  (-> fb
      (.auth)
      (.signInWithEmailAndPassword email pwd)))

(defn send! [msg]
  (-> fb
      (.database)
      (.ref)
      (.child "messages")
      (.push (clj->js msg))))

(defn subscribe! [handler]
  (-> fb
      (.database)
      (.ref)
      (.child "messages")
      (.on "value" #(-> % (.val) (js->clj :keywordize-keys true) (vals) (handler)))))
