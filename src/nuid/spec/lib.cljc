(ns nuid.spec.lib
  (:refer-clojure :exclude [select-keys])
  (:require
   [clojure.spec.alpha :as s]
   [nuid.spec.generators :as generators]))

(def keys-spec?
  (comp boolean #{`s/keys} first s/form))

(s/def ::s/keys-spec keys-spec?)

(defn -keys-spec-xf
  [[k v]]
  (if (#{:req-un :opt-un} k)
    [k (mapv (comp keyword name) v)]
    [k v]))

(defn keys-spec->map
  [keys-spec]
  (->>
   (s/form keys-spec)
   (rest)
   (partition 2)
   (into {} (map -keys-spec-xf))))

(defn keys-spec->keys
  [keys-spec]
  (->>
   (keys-spec->map keys-spec)
   (vals)
   (apply concat)))

(defn select-keys
  [keys-spec x]
  (->>
   (keys-spec->keys keys-spec)
   (clojure.core/select-keys x)))
