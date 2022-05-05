(ns nuid.spec
  (:require
   [clojure.spec.alpha :as s]
   [nuid.spec.generators :as generators]))


   ;;;
   ;;; NOTE: predicates, specs
   ;;;


(s/def ::not-empty-string
  (s/and string? seq))

(def not-empty-string?
  (partial s/valid? ::not-empty-string))

(def email-address-regex #"^.+@.+\..+$")
(def email-address?
  (comp
   boolean
   (partial re-matches email-address-regex)))

(s/def ::email-address
  (s/with-gen
    (s/and
     string?
     email-address?)
    (fn [] generators/email-address)))
