(ns ssensational.impl.naive
  (:require [ssensational.util.text :as text]
            [ssensational.util.aes :as aes]
            [ssensational.util :refer (with-index)]))

(defn encrypt [key text]
  (aes/encrypt 128 text key))

(defn encrypt-kvs [key map]
  (into { }
        (for [[word texts] map
              [j text] (with-index texts)]
          [(encrypt key (str word j)) (encrypt key text)])))

;; original "naive" implementation
;; * simply hash the word
;; * no need for that "j"
;; It was pretty quickly obvious that hashing means rainbow tables up
;; the ass, which explains the PIk() stuff in the paper. So now, your
;; "hash" will simply be keyword encryption, may refine later.
;;
;; As you were writing the below, you realized that "apply merge"
;; wouldn't jive with a bunch of identical encrypted words (plus
;; frequency analysis vulnerability), so the "j" was added.


(defn encrypt-it-all [key texts]
  (->> texts
       (text/texts->word-associations)
       (map (partial encrypt-kvs key))
       (apply merge)))



