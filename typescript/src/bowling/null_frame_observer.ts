import {FrameObserver} from './frame_observer';

export class NullFrameObserver implements FrameObserver {

    notify(score: number): void {
        // do nothing
    }

}