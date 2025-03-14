package bowling;

// This interface is the ideal abstraction of observing a frame score.
public interface FrameObserver {
    void notify(int score);
}
