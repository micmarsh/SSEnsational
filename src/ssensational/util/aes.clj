(ns ssensational.util.aes
  (:require [ssensational.util.base64 :as b64])
  (:import [javax.crypto Cipher KeyGenerator SecretKey]
           [javax.crypto.spec SecretKeySpec ]
           [java.security SecureRandom]
           [org.apache.commons.codec.binary Base64]))

(set! *warn-on-reflection* true)

(defn get-raw-key [seed ^long bit-count]
  (let [keygen (KeyGenerator/getInstance "AES")
        sr (SecureRandom/getInstance "SHA1PRNG")]
    (.setSeed sr ^bytes (bytes seed))
    (.init keygen bit-count sr)
    (.. keygen generateKey getEncoded)))

(defn get-cipher [^long mode ^String seed bit-count]
  (let [key-spec (SecretKeySpec. (get-raw-key seed bit-count) "AES")
        cipher (Cipher/getInstance "AES")]
    (.init cipher mode key-spec)
    cipher))

(defn encrypt [bit-count ^String text ^String key]
  (let [bytes (bytes text)
        ^Cipher cipher (get-cipher Cipher/ENCRYPT_MODE key bit-count)]
    (b64/encode (.doFinal cipher bytes))))

(defn decrypt [bit-count ^String text ^String key]
  (let [^Cipher cipher (get-cipher Cipher/DECRYPT_MODE key bit-count)]
    (String. ^bytes (.doFinal cipher (b64/decode text)))))


