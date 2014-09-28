(ns ssensational.util.base64
  (:import [org.apache.commons.codec.binary Base64]))

(set! *warn-on-reflection* true)

(defn bytes [^String s]
  (.getBytes s "UTF-8"))

(defn ^bytes encode [^bytes b]
  (Base64/encodeBase64 b))

(defn ^bytes decode [s]
  (Base64/decodeBase64 ^bytes (bytes s)))

