/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devlv.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SonTung
 */
public class MenuController implements Initializable {

    @FXML
    private Label label;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void toMenuAdd(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/devlv/view/MenuAdd.fxml"));
        Scene scene = new Scene(root, 600, 400);
        Stage stage = (Stage) label.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    private void toMenuDelete(MouseEvent event) {
    }

    @FXML
    private void toMenuDisplay(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/devlv/view/MenuDisplay.fxml"));
        Scene scene = new Scene(root, 600, 400);
        Stage stage = (Stage) label.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    private void exit(MouseEvent event) {
        Stage stage = (Stage) label.getScene().getWindow();
        stage.close();
    }

}
