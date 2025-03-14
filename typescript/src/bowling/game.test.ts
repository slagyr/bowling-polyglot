import { FrameObserver } from './frame_observer';
import { Game } from './game';
import { MockFrameObserver } from './mock_frame_observer';

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

    test('frame observer - all ones', () => {
        rollMany(20, 1);
        const observer: MockFrameObserver = new MockFrameObserver();
        game.score(observer);
        expect(observer.frameScores.length).toBe(10);
        expect(observer.frameScores[0]).toBe(2);
        expect(observer.frameScores[1]).toBe(4);
        expect(observer.frameScores[2]).toBe(6);
        expect(observer.frameScores[3]).toBe(8);
        expect(observer.frameScores[4]).toBe(10);
        expect(observer.frameScores[5]).toBe(12);
        expect(observer.frameScores[6]).toBe(14);
        expect(observer.frameScores[7]).toBe(16);
        expect(observer.frameScores[8]).toBe(18);
        expect(observer.frameScores[9]).toBe(20);
    });

    test('frame observer - perfect game', () => {
        rollMany(12, 10);
        const observer: MockFrameObserver = new MockFrameObserver();
        game.score(observer);
        expect(observer.frameScores.length).toBe(10);
        expect(observer.frameScores[0]).toBe(30);
        expect(observer.frameScores[1]).toBe(60);
        expect(observer.frameScores[2]).toBe(90);
        expect(observer.frameScores[3]).toBe(120);
        expect(observer.frameScores[4]).toBe(150);
        expect(observer.frameScores[5]).toBe(180);
        expect(observer.frameScores[6]).toBe(210);
        expect(observer.frameScores[7]).toBe(240);
        expect(observer.frameScores[8]).toBe(270);
        expect(observer.frameScores[9]).toBe(300);
    });

});