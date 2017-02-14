(ns designs.components
  (:require-macros [designs.ui :refer [defui]])
  (:require [rum.core :as rum]))

(defui ChatScreen
  "<div class=\"screen chat\">

  <header class=\"app-header\">
    <div class=\"screen-title\">Profile</div>
    <button class=\"menu-btn\">☰</button>
  </header>

  <main>

    <ul class=\"chat-list\">

      <li class=\"chat-msg\">
        <img src=\"http://i.imgur.com/1rm5Qq6.png\" class=\"avatar\">
        <div class=\"bubble\">
          <div class=\"bubble-uname\">Alexey Veligura</div>
          <div class=\"body\">Так как многие не попадают в том числе я ). С вас хорошая трансляция или запись ))</div>
        </div>
      </li>

      <li class=\"chat-msg owner\">
        <img src=\"http://i.imgur.com/1rm5Qq6.png\" class=\"avatar\">
        <div class=\"bubble\">
          <div class=\"bubble-uname_owner\">Alexey Veligura</div>
          <div class=\"body\">Так как многие не попадают в том числе я ). С вас хорошая трансляция или запись ))</div>
        </div>
      </li>
    </ul>

  </main>

  <footer class=\"app-footer\">
    <form class=\"message\">
      <input type=\"text\" class=\"message\">
    </form>
  </footer>

</div>")

(defui MenuButton
  "<button class=\"menu-btn\">☰</button>")

(defui Header
  "<header class=\"app-header\">
  <div class=\"screen-title\">Profile</div>
  <button class=\"menu-btn\">☰</button>
</header>")


(defui ChatList
  "<main>
  <ul class=\"chat-list\"></ul>
</main>")

(defui TimeGroup
  "<li class=\"time\">FEB 2, 8:55PM</li>")

(defui ChatMessageSent
  "<li class=\"chat-msg owner\">
  <img src=\"http://i.imgur.com/1rm5Qq6.png\" class=\"avatar\">
  <div class=\"bubble\">
    <div class=\"bubble-uname_owner\">Alexey Veligura</div>
    <div class=\"body\">Так как многие не попадают в том числе я ). С вас хорошая трансляция или запись ))</div>
  </div>
</li>")

(defui ChatMessageReceived
  "<li class=\"chat-msg\">
  <img src=\"http://i.imgur.com/1rm5Qq6.png\" class=\"avatar\">
  <div class=\"bubble\">
    <div class=\"bubble-uname\">Alexey Veligura</div>
    <div class=\"body\">Так как многие не попадают в том числе я ). С вас хорошая трансляция или запись ))</div>
  </div>
</li>")

(defui Avatar
  "<img src=\"http://i.imgur.com/1rm5Qq6.png\" class=\"avatar\">")

(defui ChatMessageBubble
  "<div class=\"bubble\">
  <div class=\"bubble-uname_owner\">Alexey Veligura</div>
  <div class=\"body\">Так как многие не попадают в том числе я ). С вас хорошая трансляция или запись ))</div>
</div>")


(defui MessageInput
  "<footer class=\"app-footer\">
  <form class=\"message\">
    <input type=\"text\" class=\"message\">
  </form>
</footer>")
