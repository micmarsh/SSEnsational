(ns ssensational.impl.adaptive
  (:require [ssensational.util :refer (fake-hash with-index)]
            [ssensational.util.text :as text]))

(defn word-associations->lookup-table [associations]
  (into { }
        (for [[i [word texts]] (with-index associations)
              [j text] (with-index texts)]
          [(fake-hash (str word j)) (fake-hash text)])))

(def generate-index
  (comp word-associations->lookup-table
        text/texts->word-associations))
