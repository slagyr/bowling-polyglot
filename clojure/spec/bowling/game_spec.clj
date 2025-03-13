(ns bowling.game-spec
  (:require [speclj.core :refer :all]
            [bowling.game :refer :all]))

(describe "Bowling game"

  (it "gutter game"
    (should= 0 (score [0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0])))

  (it "all 1 pins"
    (should= 20 (score (take 20 (repeat 1)))))

  (it "a spare"
    (let [rolls (concat [5 5 5] (take 17 (repeat 0)))]
      (should= 20 (score rolls))))

  (it "a strike"
    (let [rolls (concat [10 4 5] (take 16 (repeat 0)))]
      (should= 28 (score rolls))))

  (it "perfect game"
    (should= 300 (score (take 12 (repeat 10)))))

  (it "heart breaker"
    (should= 299 (score (concat (take 11 (repeat 10)) [9]))))

  )
