package game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardGameTest {

    @Test
    void canClick() {
        BoardGame b = new BoardGame();

        b.click(1, 1);
        b.click(4, 2);
        b.click(2, 1);
        assertFalse(b.canClick(1, 1));
        assertTrue(b.canClick(3, 3));
        assertFalse(b.canClick(2, 1));

    }

    @Test
    void click() {
        BoardGame b = new BoardGame();
        b.click(1, 1);
        b.click(2, 2);
        assertArrayEquals(b.cellsState, new int[][]{
                {0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 0, 2, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        });

        b.click(4,4);
        assertArrayEquals(b.cellsState, new int[][]{
                {0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 0, 2, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1}
        });
    }

    @Test
    void determineWinner() {
        BoardGame b1 = new BoardGame(new int[][]{
            {1, 0, 2, 1, 1},
            {2, 1, 1, 2, 0},
            {0, 1, 2, 1, 2},
            {1, 0, 2, 1, 1},
            {2, 1, 1, 2, 2}
        });
        assertEquals(2, b1.determineWinner());
    }

    @Test
    void getTurns() {
        BoardGame b = new BoardGame();

        b.click(1, 3);
        b.click(2, 2);
        b.click(0, 3);
        b.click(1, 3);
        assertEquals(3, b.getTurns());

        b.click(3, 3);
        assertEquals(4, b.getTurns());

    }

}