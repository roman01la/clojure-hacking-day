(ns cheetah.api
  (:require [cljsjs.firebase]
            [goog.object :as gobj]))

(set! *warn-on-infer* true)

(def ^js/firebase fb js/firebase)

(def config
  {:apiKey "AIzaSyDCLdiimM3GemnQ6vB2j3esl23nWwSKRvc"
   :authDomain "cheetah-3deed.firebaseapp.com"
   :databaseURL "https://cheetah-3deed.firebaseio.com"
   :storageBucket "cheetah-3deed.appspot.com"
   :messagingSenderId "1057267043259"})

(defonce init-app (.initializeApp fb (clj->js config)))

(def ^js/firebase.database.Reference conn (-> fb .database .ref))

(defn auth-twitter! []
  (let [auth (.auth fb)]
    (if-let [^js/Object user (gobj/get auth "currentUser")]
      (js/Promise.resolve (-> user .toJSON  (js->clj :keywordize-keys true)))
      (-> auth
          (.signInWithPopup (fb.auth.TwitterAuthProvider.))
          (.then #(gobj/get % "user"))
          (.then #(.toJSON ^js/Object %))
          (.then #(js->clj % :keywordize-keys true))))))

(defn sign-out! []
  (-> fb .auth .signOut))

(defn send! [msg]
  (-> conn
      (.child "messages")
      (.push (clj->js msg))))

(defn subscribe! [key handler]
  (-> conn
      (.child key)
      (.on "value" #(-> ^js/firebase.database.ServerValue % .val (js->clj :keywordize-keys true) vals handler))))
