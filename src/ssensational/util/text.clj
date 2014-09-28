(ns ssensational.util.text
  (:require [clojure.string :as string]))

(defn text->words [text]
  (as-> text *
      (string/split * #" ")
      (map string/lower-case *)))

(defn texts->word-associations [texts]
  (apply merge-with concat
         (map
          (fn [text]
            (let [words (text->words text)]
              (zipmap words (repeat [text]))))
          texts)))
