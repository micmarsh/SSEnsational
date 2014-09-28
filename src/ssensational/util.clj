(ns ssensational.util
  (:require [ssensational.util.aes :as aes]) )

(def with-index (partial map-indexed vector))

(defn fake-hash [stuff]
  (aes/encrypt 256 stuff "fake key"))
