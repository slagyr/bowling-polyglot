(ns bowling.game)

(defn score-for-frame [rolls]
  (+ (first rolls) (second rolls)))

(defn score-frame [score rolls]
  (+ score (score-for-frame rolls)))

(defn spare? [rolls]
  (= 10 (score-for-frame rolls)))

(defn score-spare [score rolls]
  (+ score 10 (nth rolls 2)))

(defn strike? [rolls]
  (= 10 (first rolls)))

(defn score-strike [score rolls]
  (+ score 10 (+ (second rolls) (nth rolls 2))))

(defmulti notify-frame (fn [observer _frame-score] observer))
(defmethod notify-frame :default [_observer _frame-score] #_do-nothing)

(defn score
  ([rolls] (score rolls nil))
  ([rolls observer]
   (loop [frame 1 score 0 rolls rolls]
     (when (> frame 1)
       (notify-frame observer score))
     (if (> frame 10)
       score
       (let [frame (inc frame)]
         (cond (strike? rolls) (recur frame (score-strike score rolls) (drop 1 rolls))
               (spare? rolls) (recur frame (score-spare score rolls) (drop 2 rolls))
               :else (recur frame (score-frame score rolls) (drop 2 rolls))))))))
