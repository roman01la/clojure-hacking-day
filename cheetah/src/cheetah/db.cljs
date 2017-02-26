(ns cheetah.db)

(def initial-state
  {:route [:sign-in]
   :user {:name nil
          :avatar nil}
   :messages []
   :rooms []
   :menu {:visible? false}})

(defonce db (atom initial-state))
