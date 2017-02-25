(ns cheetah.db)

(defonce db (atom {:route [:sign-in]
                   :user {:name nil}
                   :rooms {}}))
