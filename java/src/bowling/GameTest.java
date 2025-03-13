package bowling;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameTest {

    private Game game;

    @Before
    public void setup() {
        game = new Game();
    }

    @Test
    public void gutterGame() throws Exception {
        rollMany(20, 0);
        assertEquals(0, game.score());
    }

    @Test
    public void onePin() throws Exception {
        game.roll(1);
        rollMany(19, 0);
        assertEquals(1, game.score());
    }

    @Test
    public void allOnes() throws Exception {
        rollMany(20, 1);
        assertEquals(20, game.score());
    }

    @Test
    public void spare() throws Exception {
        game.roll(9);
        game.roll(1);
        game.roll(4);
        rollMany(17, 0);
        assertEquals(18, game.score());
    }

    @Test
    public void strike() throws Exception {
        game.roll(10);
        game.roll(4);
        game.roll(5);
        rollMany(16, 0);
        assertEquals(28, game.score());
    }

    @Test
    public void perfectGame() throws Exception {
        rollMany(12, 10);
        assertEquals(300, game.score());
    }

    @Test
    public void allSpare() throws Exception {
        rollMany(21, 5);
        assertEquals(150, game.score());
    }

    @Test
    public void heartBreaker() throws Exception {
        rollMany(11, 10);
        game.roll(9);
        assertEquals(299, game.score());
    }

    private void rollMany(int rolls, int pins) {
        for(int i = 0; i < rolls; i++) {
            game.roll(pins);
        }
    }
}