import {FrameObserver} from './frame_observer';

export class MockFrameObserver implements FrameObserver {

    public frameScores: number[];

    constructor() {
        this.frameScores = [];
    }

    notify(score: number): void {
        this.frameScores.push(score);
    }

}