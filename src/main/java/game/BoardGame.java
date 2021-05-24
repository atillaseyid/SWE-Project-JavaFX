package game;

public class BoardGame {
    public int[][] cellsState;

    public int turn;

    public final int[][] INITIAL = {
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
    };

    public final int[][] testInitial = {
            {1, 1, 1, 0, 0},
            {0, 0, 0, 1, 0},
            {0, 2, 0, 1, 0},
            {0, 0, 2, 0, 0},
            {0, 0, 0, 2, 0}
    };
    public int count = 0;

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
        if(canClick(i, j) && (count % 2 == 0)){
            cellsState[i][j] = 1;
            count++;
        }
        if(canClick(i, j) && (count % 2 == 1)){
            cellsState[i][j] = 2;
            count++;
        }
    }

    public int determineWinner() {
        for(int i = 0; i < cellsState.length; i++){
            for(int j = 0; j < cellsState[0].length; j++) {
                if ((j<=2 && cellsState[i][j] == 1 && cellsState[i][j + 1] == 1 && cellsState[i][j + 2] == 1) ||
                        (i<=2 && cellsState[i][j] == 1 && cellsState[i + 1][j] == 1 && cellsState[i + 2][j] == 1) ||
                        (i<=2 && j<=2 && cellsState[i][j] == 1 && cellsState[i + 1][j + 1] == 1 && cellsState[i + 2][j + 2] == 1) ||
                        ((i>=2 && j<=2 && cellsState[i][j] == 1 && cellsState[i - 1][j + 1] == 1 && cellsState[i - 2][j + 2] == 1))) {
                    return 1;
                }
                if ((j<=2 && cellsState[i][j] == 2 && cellsState[i][j + 1] == 2 && cellsState[i][j + 2] == 2) ||
                        (i<=2 && cellsState[i][j] == 2 && cellsState[i + 1][j] == 2 && cellsState[i + 2][j] == 2) ||
                        (i<=2 && j<=2 && cellsState[i][j] == 2 && cellsState[i + 1][j + 1] == 2 && cellsState[i + 2][j + 2] == 2) ||
                        ((i>=2 && j<=2 && cellsState[i][j] == 2 && cellsState[i - 1][j + 1] == 2 && cellsState[i - 2][j + 2] == 2))) {
                    return 2;
                }
            }
        }
        return 0;
    }

    public int getTurns(){
        turn = 0;
        for(int i = 0; i < cellsState.length; i++){
            for(int j = 0; j < cellsState[0].length; j++){
                if(cellsState[i][j] != 0)
                    turn++;
            }
        }
        return turn;
    }

    public void printGameBoard() {
        for (int i = 0; i < cellsState.length; i++) {
            for (int j = 0; j < cellsState[0].length; j++) {
                System.out.print(cellsState[i][j]);
            }
            System.out.println();
        }
    }

}

