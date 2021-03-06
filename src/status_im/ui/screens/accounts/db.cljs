(ns status-im.ui.screens.accounts.db
  (:require-macros [status-im.utils.db :refer [allowed-keys]])
  (:require [cljs.spec.alpha :as spec]
            status-im.utils.db
            status-im.ui.screens.network-settings.db
            [status-im.constants :as const]))

(defn valid-length? [password]
  (>= (count password) const/min-password-length))

(spec/def ::password  (spec/and :global/not-empty-string valid-length?))

(spec/def :account/address :global/address)
(spec/def :account/name :global/not-empty-string)
(spec/def :account/public-key :global/public-key)
;;not used
(spec/def :account/email nil?)
(spec/def :account/signed-up? (spec/nilable boolean?))
(spec/def :account/last-updated (spec/nilable int?))
(spec/def :account/last-sign-in (spec/nilable int?))
(spec/def :account/updates-private-key :global/not-empty-string)
(spec/def :account/updates-public-key :global/not-empty-string)
(spec/def :account/photo-path (spec/nilable string?))
(spec/def :account/debug? (spec/nilable boolean?))
(spec/def :account/status (spec/nilable string?))
(spec/def :account/network (spec/nilable string?))
(spec/def :account/networks (spec/nilable :networks/networks))
(spec/def :account/wnode (spec/nilable string?))
(spec/def :account/settings (spec/nilable (spec/map-of keyword? any?)))
(spec/def :account/signing-phrase :global/not-empty-string)
(spec/def :account/sharing-usage-data? (spec/nilable boolean?))

(spec/def :accounts/account (allowed-keys
                              :req-un [:account/name :account/address :account/public-key
                                       :account/photo-path :account/signing-phrase]
                              :opt-un [:account/debug? :account/status :account/last-updated
                                       :account/updates-private-key :account/updates-public-key
                                       :account/email :account/signed-up? :account/network
                                       :account/networks :account/settings :account/wnode
                                       :account/last-sign-in :account/sharing-usage-data?]))

(spec/def :accounts/accounts (spec/nilable (spec/map-of :account/address :accounts/account)))

;;id of logged in account
(spec/def :accounts/current-account-id (spec/nilable string?))

;;used during creating account
(spec/def :accounts/create (spec/nilable map?))
;;used during recovering account
(spec/def :accounts/recover (spec/nilable map?))
;;used during logging
(spec/def :accounts/login (spec/nilable map?))
