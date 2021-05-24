package gui;

import game.BoardGame;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class BoardGameController {

    @FXML
    private GridPane board;

    BoardGame bg;

    @FXML
    private void initialize() {
        bg = new BoardGame();
        displayBoard();
    }

    @FXML
    private void handleMouseClick(MouseEvent event) {
        var square = (StackPane) event.getSource();
        var row = GridPane.getRowIndex(square);
        var col = GridPane.getColumnIndex(square);
        System.out.printf("Click on square (%d,%d)\n", row, col);
//        var coin = (Circle) square.getChildren().get(0);
        bg.click(row, col);
        displayBoard();
        if (bg.determineWinner() == 1) {
            System.out.println("Second Player is Winner");
            board.setDisable(true);
        }
        if (bg.determineWinner() == 2) {
            System.out.println("First Player is Winner");
            board.setDisable(true);
        }
    }


    private void displayBoard(){
        for (int i = 0; i < board.getRowCount(); i++) {
            for (int j = 0; j < board.getColumnCount(); j++) {
                var square = new StackPane();
                square.getStyleClass().add("square");
                var piece = new Circle(50);
                if(bg.cellsState[i][j] == 1)
                    piece.setFill(Color.RED);
                if(bg.cellsState[i][j] == 2)
                    piece.setFill(Color.BLUE);
                if(bg.cellsState[i][j] == 0)
                    piece.setFill(Color.TRANSPARENT);
                square.getChildren().add(piece);
                square.setOnMouseClicked(this::handleMouseClick);
                board.add(square, j, i);
            }
        }
    }

//    private Color nextColor(Color color) {
//
//    }


}
