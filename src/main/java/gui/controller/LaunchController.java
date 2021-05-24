package gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LaunchController {
    private FXMLLoader fxmlLoader = new FXMLLoader();

    @FXML
    private TextField player1NameTextField;

    @FXML
    private TextField player2NameTextField;

    public static String playerName1;
    public static String playerName2;

    @FXML
    private Label errorLabel;

    public void startAction(ActionEvent actionEvent) throws IOException {
        errorLabel.setText("");

        if(player1NameTextField.getText().isEmpty() && player2NameTextField.getText().isEmpty()){
            errorLabel.setText("Players, please, enter your names...");

        }
        else if(player1NameTextField.getText().isEmpty()){
            errorLabel.setText("Player1, enter your name...");

        }
        else if(player2NameTextField.getText().isEmpty()){
            errorLabel.setText("Player2, enter your name...");

        }
        else{
            playerName1 = player1NameTextField.getText();
            playerName2 = player2NameTextField.getText();
            fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/game.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }
}
