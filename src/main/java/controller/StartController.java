package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class StartController {


    @FXML
    private TextField player_1;

    @FXML
    private TextField player_2;

    @FXML
    private Label errorLabel;

    public void startAction(ActionEvent actionEvent) throws IOException {
        if (player_1.getText().isEmpty()||player_2.getText().isEmpty()) {
            errorLabel.setText("* Player name is empty!");

        } else   {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/fxml/game14.fxml"));
            //Parent root = fxmlLoader.load();
            fxmlLoader.<Game14RowsController>getController().initdata(player_1.getText() ,player_2.getText());
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.show();
            //log.info("Player1 is set to {}, loading game scene.", player_1.getText());
            //log.info("Player2 is set to {}, loading game scene.", player_2.getText());
        }

    }


}

