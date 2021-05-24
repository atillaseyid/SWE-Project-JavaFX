package gui.controller;

import Helpers.DataManager;
import Models.GameState;
import game.BoardGame;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDateTime;
/**
 * Class which collects user and game data.Handles the mouse-clicking and displaying board functions
 */

public class BoardGameController {
    private FXMLLoader fxmlLoader = new FXMLLoader();



    public LocalDateTime time;



    public GameState gamestate = new GameState();

    @FXML
    private GridPane board;

    BoardGame bg;

    @FXML
    private void initialize() {
        gamestate.setPlayer1(LaunchController.playerName1);
       // player1 = LaunchController.playerName1;
        gamestate.setPlayer2(LaunchController.playerName2);
        //player2 = LaunchController.playerName2;
        time = LocalDateTime.now();
        //timeInText = time.toString();
        gamestate.setTimeInText(time.toString());
        System.out.println(gamestate.getTimeInText());//time to db
        System.out.println(gamestate.getPlayer1() + " vs " + gamestate.getPlayer2());//players to db
        bg = new BoardGame();
        displayBoard();
    }

    @FXML
    private void handleMouseClick(MouseEvent event) {
        var square = (StackPane) event.getSource();
        var row = GridPane.getRowIndex(square);
        var col = GridPane.getColumnIndex(square);
        System.out.printf("Click on square (%d,%d)\n", row, col);
        bg.click(row, col);
        displayBoard();

        if (bg.determineWinner() == 1) {
            //winner = player2;
            gamestate.setWinner(gamestate.getPlayer2());

            fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/scores.fxml"));
            try {
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

            board.setDisable(true);
        }
        if (bg.determineWinner() == 2) {
            //winner = player1;
            gamestate.setWinner(gamestate.getPlayer1());
            fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/scores.fxml"));
            try {
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            board.setDisable(true);

        }
        System.out.println(bg.getTurns());// turns to db
        System.out.println(gamestate.getWinner()); // winner to db
        if(gamestate.getWinner() != null){
            gamestate.setTurn(bg.getTurns());
            DataManager.save(gamestate);
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
}
