package gui.controller;

import Models.GameState;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;

import javax.swing.text.TableView;

public class GameScoresController {

    @FXML
    public void exit(ActionEvent actionEvent){
        System.exit(1);
    }

}
