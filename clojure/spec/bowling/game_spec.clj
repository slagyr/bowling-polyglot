(ns bowling.game-spec
  (:require [speclj.core :refer :all]
            [speclj.stub :as stub]
            [bowling.game :refer :all]))

(describe "Bowling game"

  (it "gutter game"
    (should= 0 (score [0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0])))

  (it "1 pin"
    (let [rolls (concat [1] (take 19 (repeat 0)))]
      (should= 1 (score rolls))))

  (it "all 1s"
    (should= 20 (score (take 20 (repeat 1)))))

  (it "a spare"
    (let [rolls (concat [5 5 5] (take 17 (repeat 0)))]
      (should= 20 (score rolls))))

  (it "all 5s"
    (let [rolls (take 21 (repeat 5))]
      (should= 150 (score rolls))))

  (it "a strike"
    (let [rolls (concat [10 4 5] (take 16 (repeat 0)))]
      (should= 28 (score rolls))))

  (it "perfect game"
    (should= 300 (score (take 12 (repeat 10)))))

  (it "heart breaker"
    (should= 299 (score (concat (take 11 (repeat 10)) [9]))))

  (context "frame observer"

    (with-stubs)

    (it "is notified of the score at each frame - all 1s"
      (with-redefs [notify-frame (stub :notify-frame)]
        (score (take 20 (repeat 1)) :test)
        (should-have-invoked :notify-frame {:times 10})
        (should= [[2] [4] [6] [8] [10] [12] [14] [16] [18] [20]] (stub/invocations-of :notify-frame))))

    (it "is notified of the score at each frame - perfect game"
      (with-redefs [notify-frame (stub :notify-frame)]
        (score (take 12 (repeat 10)) :test)
        (should-have-invoked :notify-frame {:times 10})
        (should= [[30] [60] [90] [120] [150] [180] [210] [240] [270] [300]] (stub/invocations-of :notify-frame))))

    )

  )
