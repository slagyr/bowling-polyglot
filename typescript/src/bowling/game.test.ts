// src/bowling/game.test.ts
import { Game } from './game';

describe('Game', () => {
    let game: Game;

    beforeEach(() => {
        game = new Game();
    });

    const rollMany = (rolls: number, pins: number): void => {
        for (let i = 0; i < rolls; i++) {
            game.roll(pins);
        }
    };

    test('gutter game scores 0', () => {
        rollMany(20, 0);
        expect(game.score()).toBe(0);
    });

    test('one pin scores 1', () => {
        game.roll(1);
        rollMany(19, 0);
        expect(game.score()).toBe(1);
    });

    test('all ones scores 20', () => {
        rollMany(20, 1);
        expect(game.score()).toBe(20);
    });

    test('spare adds next roll', () => {
        game.roll(9);
        game.roll(1); // Spare
        game.roll(4);
        rollMany(17, 0);
        expect(game.score()).toBe(18);
    });

    test('strike adds next two rolls', () => {
        game.roll(10); // Strike
        game.roll(4);
        game.roll(5);
        rollMany(16, 0);
        expect(game.score()).toBe(28);
    });

    test('perfect game', () => {
        rollMany(12, 10);
        expect(game.score()).toBe(300);
    });

    test('all spares', () => {
        rollMany(21, 5);
        expect(game.score()).toBe(150);
    });

    test('heart breaker', () => {
        rollMany(11, 10);
        game.roll(9);
        expect(game.score()).toBe(299);
    });
});