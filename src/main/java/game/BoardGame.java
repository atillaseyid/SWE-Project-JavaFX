package game;

public class BoardGame {
    public int[][] cellsState;

    public final int[][] INITIAL = {
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
    };

    public final int[][] testInitial = {
            {1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
    };
    public static int count = 0;

    public BoardGame(){
        cellsState = INITIAL;
    }

    public BoardGame(int[][] testCase)
    {
        cellsState = testCase;
    }

    public boolean canClick(int i, int j){
        return cellsState[i][j] == 0;
    }

    public void click(int i, int j){
        if(canClick(i, j) && count % 2 == 0){
            cellsState[i][j] = 1;
            count++;
        }
        if(canClick(i, j) && count % 2 == 1){
            cellsState[i][j] = 2;
            count++;
        }
    }

    public int determineWinner() {
        for (int i = 0; i < cellsState.length - 2; i++) {
            for (int j = 0; j < cellsState[0].length - 2; j++) {
                if ((cellsState[i][j] == 1 && cellsState[i][j + 1] == 1 && cellsState[i][j + 2] == 1)
                        || (cellsState[i][j] == 1 && cellsState[i + 1][j] == 1 && cellsState[i + 2][j] == 1)
                        || (cellsState[i][j] == 1 && cellsState[i + 1][j + 1] == 1 && cellsState[i + 2][j + 2] == 1)) {
                    return 2;
                }
                if ((cellsState[i][j] == 2 && cellsState[i][j + 1] == 2 && cellsState[i][j + 2] == 2)
                        || (cellsState[i][j] == 2 && cellsState[i + 1][j] == 2 && cellsState[i + 2][j] == 2)
                        || (cellsState[i][j] == 2 && cellsState[i + 1][j + 1] == 2 && cellsState[i + 2][j + 2] == 2)) {
                    return 1;
                }
            }
        }
        return 0;
    }


    public void print() {
        for (int i = 0; i < cellsState.length; i++) {
            for (int j = 0; j < cellsState[0].length; j++) {
                System.out.print(cellsState[i][j]);
            }
            System.out.println();
        }
    }

}

