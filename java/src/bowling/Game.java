package bowling;

public class Game {

    private int[] rolls;
    private int rollIndex;

    public Game() {
        rolls = new int[21];
    }

    public void roll(int pins) {
        rolls[rollIndex++] = pins;
    }

    public int score() {
        int score = 0;
        int roll = 0;
        for(int frame = 0; frame < 10; frame++) {
            if(isStrike(roll)) {
                score += scoreStrike(roll);
                roll += 1;
            } else if(isSpare(roll)) {
                score += scoreSpare(roll);
                roll += 2;
            } else {
                score += scoreFrame(roll);
                roll += 2;
            }
        }
        return score;
    }

    private int scoreStrike(int roll) {
        return 10 + scoreFrame(roll + 1);
    }

    private boolean isStrike(int roll) {
        return rolls[roll] == 10;
    }

    private int scoreFrame(int roll) {
        return rolls[roll] + rolls[roll + 1];
    }

    private int scoreSpare(int roll) {
        return 10 + rolls[roll + 2];
    }

    private boolean isSpare(int roll) {
        return scoreFrame(roll) == 10;
    }
}