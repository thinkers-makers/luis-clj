(ns luis-clj.core-test
  (:require [clojure.test :refer :all]
            [luis-clj.core :refer :all]))

(deftest creating-luis-endpoint
  (testing "LUIS endpoint built from request map"
    (let [request-data {:id "id"
                        :subscription-key "subscription-key"
                        :utterance "utterance"}
          {:keys [protocol
                  host
                  path
                  protocol
                  query]} (create-url request-data)]
      (is (= "https" protocol))
      (is (= "api.projectoxford.ai" host))
      (is (= "/luis/v1/application" path))
      (is (= "utterance" (:q query)))
      (is (= "id" (:id query)))
      (is (= "subscription-key" (:subscription-key query))))))

(deftest adding-context-to-options
  (testing "context is combined with oprions"
    (let [result (opts-with-context {:key :val})]
      (is (= 2000 (result :timeout)))
      (is (= :val (get-in result [:context :key])))))

  (testing "default error function is provided"
    (let [result (opts-with-context {})]
      (is (= println (get-in result [:context :error-fn])))))

  (testing "alternative error function can be provided in context"
    (let [result (opts-with-context {:error-fn str})]
      (is (= str (get-in result [:context :error-fn]))))))
