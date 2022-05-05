(ns nuid.spec.generators
  (:require
   [clojure.spec.alpha :as s]
   [clojure.spec.gen.alpha :as gen]
   [clojure.test.check.generators]))


    ;;;
    ;;; NOTE: generators
    ;;;


(def not-empty-string-alphanumeric
  (->>
   (gen/string-alphanumeric)
   (gen/such-that seq)))

(def email-address
  (->>
   (gen/tuple nuid.spec.generators/not-empty-string-alphanumeric
              nuid.spec.generators/not-empty-string-alphanumeric
              (s/gen #{"com" "edu" "gov" "io"}))
   (gen/fmap (fn [[name host tld]] (str name "@" host "." tld)))))
