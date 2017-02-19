(ns designs.components
  (:require-macros [designs.ui :refer [defui]])
  (:require [rum.core :as rum]))

(defui ChatScreen
  "<div class=\"screen chat\">

  <header class=\"app-header\">
    <div class=\"left\">
      <button class=\"back btn\"><</button>
      <div class=\"screen-title\">
        <span>ClojureScript</span>
        <div class=\"room-users-count\">Online: 3 / 5</div>
      </div>
    </div>
    <div class=\"right\">
      <button class=\"menu-btn\">☰</button>
    </div>
  </header>

  <main>

    <ul class=\"chat-list\">

      <li class=\"chat-msg\">
        <img src=\"https://2013.webrebels.org/gfx/speaker_david.jpg\" class=\"avatar\">
        <div class=\"bubble\">
          <div class=\"bubble-uname\">David Nolen</div>
          <div class=\"body\">The amount of innovation and invention in the ClojureScript community is nothing short of stunning.</div>
        </div>
      </li>

      <li class=\"chat-msg owner\">
        <img src=\"https://media.licdn.com/mpr/mpr/shrinknp_200_200/p/3/000/024/157/2fd1a24.jpg\" class=\"avatar\">
        <div class=\"bubble\">
          <div class=\"bubble-uname_owner\">Rich Hickey</div>
          <div class=\"body\">Cool!</div>
        </div>
      </li>

    </ul>

  </main>

  <footer class=\"app-footer\">
    <form class=\"message\">
      <input type=\"text\" class=\"message\">
      <button class=\"btn send-btn\">Send</button>
    </form>
  </footer>

</div>")

(defui MenuButton
  "<button class=\"menu-btn\">☰</button>")

(defui Header
  "<header class=\"app-header\">
  <div class=\"left\">
    <button class=\"back btn\"><</button>
    <div class=\"screen-title\">
      <span>ClojureScript</span>
      <div class=\"room-users-count\">Online: 3 / 5</div>
    </div>
  </div>
  <div class=\"right\">
    <button class=\"menu-btn\">☰</button>
  </div>
</header>")


(defui ChatList
  "<main>
  <ul class=\"chat-list\"></ul>
</main>")

(defui TimeGroup
  "<li class=\"time\">FEB 2, 8:55PM</li>")

(defui ChatMessageSent
  "<li class=\"chat-msg owner\">
  <img src=\"https://media.licdn.com/mpr/mpr/shrinknp_200_200/p/3/000/024/157/2fd1a24.jpg\" class=\"avatar\">
  <div class=\"bubble\">
    <div class=\"bubble-uname_owner\">Rich Hickey</div>
    <div class=\"body\">Cool!</div>
  </div>
</li>")

(defui ChatMessageReceived
  "<li class=\"chat-msg\">
  <img src=\"https://2013.webrebels.org/gfx/speaker_david.jpg\" class=\"avatar\">
  <div class=\"bubble\">
    <div class=\"bubble-uname\">David Nolen</div>
    <div class=\"body\">The amount of innovation and invention in the ClojureScript community is nothing short of stunning.</div>
  </div>
</li>")

(defui Avatar
  "<img src=\"http://i.imgur.com/1rm5Qq6.png\" class=\"avatar\">")

(defui ChatMessageBubble
  "<div class=\"bubble\">
  <div class=\"bubble-uname_owner\">Rich Hickey</div>
  <div class=\"body\">Cool!</div>
</div>")


(defui MessageInput
  "<footer class=\"app-footer\">
  <form class=\"message\">
    <input type=\"text\" class=\"message\">
    <button class=\"btn send-btn\">Send</button>
  </form>
</footer>")
