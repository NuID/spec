(ns nuid.spec.generators
  (:require
   #?@(:clj
       [[clojure.alpha.spec :as s]
        [clojure.alpha.spec.gen :as gen]]
       :cljs
       [[clojure.spec.alpha :as s]
        [clojure.spec.gen.alpha :as gen]
        [clojure.test.check.generators]])))

(def non-empty-string-alphanumeric
  (->>
   (gen/string-alphanumeric)
   (gen/such-that seq)))

(def email-address
  (->>
   (repeat 3 non-empty-string-alphanumeric)
   (apply gen/tuple)
   (gen/fmap (fn [[name host tld]] (str name "@" host "." tld)))))
