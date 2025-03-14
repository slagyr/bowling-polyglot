package bowling;

import java.util.ArrayList;
import java.util.List;

public class MockFrameObserver implements FrameObserver{
    public List<Integer> frameScores = new ArrayList<>();

    @Override
    public void notify(int score) {
        frameScores.add(score);
    }
}
