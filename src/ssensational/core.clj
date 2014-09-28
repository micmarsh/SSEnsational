(ns ssensational.core
  (:require [ssensational.util.aes :as aes]))


(def with-index (partial map-indexed vector))

(defn- fake-hash [stuff]
  (aes/encrypt 256 stuff "fake key"))

(defn word-associations->lookup-table [associations]
  (into { }
        (for [[i [word texts]] (with-index associations)
              [j text] (with-index texts)]
          [(fake-hash (str word j)) (fake-hash text)])))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))
