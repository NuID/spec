(ns nuid.spec.generators
  (:require
   [clojure.spec.alpha :as s]
   [clojure.spec.gen.alpha :as gen]
   [clojure.test.check.generators]))

(def not-empty-string-alphanumeric
  (->>
   (gen/string-alphanumeric)
   (gen/such-that seq)))

(def email-address
  (->>
   (repeat 3 not-empty-string-alphanumeric)
   (apply gen/tuple)
   (gen/fmap (fn [[name host tld]] (str name "@" host "." tld)))))
