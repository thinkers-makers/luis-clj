(ns luis-clj.core
  (:require [org.httpkit.client :as http]
            [cemerick.url :refer [url]]
            [cheshire.core :refer [parse-string]]))

(def base-url "https://api.projectoxford.ai/luis/v1/application")
(def base-opts {:timeout 2000})
(def base-context {:error-fn println})

(defmulti handle-intent :intent)

(defn create-url
  [{:keys [id subscription-key utterance]}]
  (-> (url base-url)
      (assoc-in [:query :id] id)
      (assoc-in [:query :subscription-key] subscription-key)
      (assoc-in [:query :q] utterance)))

(defn handle-error
  [{:keys [error-fn]} error]
  (str "An error has occured: " (error-fn error)))

(defn callback
  [{:keys [body error opts]}]
  (if error
    (handle-error (:context opts) error)
    (let [{:keys [intents]} (parse-string body true)]
      (handle-intent (first intents) (:context opts)))))

(defn opts-with-context
  [context]
  (->> context
       (merge base-context)
       (assoc base-opts :context)))

(defn predict-intent
  "Submits a query to LUIS.ai production endpoint.

   query:
      {:id ''
       :subscription-key ''
       :utterance ''}

   context:
       {:error-fn fn}"
  [query context]
  (let [endpoint (-> query create-url str)
        opts (opts-with-context context)]
    (http/get endpoint opts callback)))
