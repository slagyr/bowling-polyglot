package bowling;

// NullObject Pattern - This implementation of FrameObserver does absolutely nothing.
public class NullFrameObserver implements FrameObserver{
    @Override
    public void notify(int score) {
//         do nothing
    }
}
