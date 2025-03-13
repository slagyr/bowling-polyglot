// src/bowling/game.ts
export class Game {
    private rolls: number[] = new Array(21).fill(0); // Pre-filled array for simplicity
    private rollIndex: number = 0;

    public roll(pins: number): void {
        this.rolls[this.rollIndex++] = pins;
    }

    public score(): number {
        let score = 0;
        let roll = 0;
        for (let frame = 0; frame < 10; frame++) {
            if (this.isStrike(roll)) {
                score += this.scoreStrike(roll);
                roll += 1;
            } else if (this.isSpare(roll)) {
                score += this.scoreSpare(roll);
                roll += 2;
            } else {
                score += this.scoreFrame(roll);
                roll += 2;
            }
        }
        return score;
    }

    private scoreStrike(roll: number): number {
        return 10 + this.scoreFrame(roll + 1);
    }

    private isStrike(roll: number): boolean {
        return this.rolls[roll] === 10;
    }

    private scoreFrame(roll: number): number {
        return this.rolls[roll] + this.rolls[roll + 1];
    }

    private scoreSpare(roll: number): number {
        return 10 + this.rolls[roll + 2];
    }

    private isSpare(roll: number): boolean {
        return this.scoreFrame(roll) === 10 && !this.isStrike(roll);
    }
}