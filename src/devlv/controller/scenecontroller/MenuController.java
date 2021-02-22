/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devlv.controller.scenecontroller;

import devlv.controller.SceneChanger;
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
    
    SceneChanger sc = SceneChanger.getInstance();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void toMenuAdd(MouseEvent event) throws IOException {
        sc.switchScene(event, sc.MENU_ADD);
    }

    @FXML
    private void toMenuDisplay(MouseEvent event) throws IOException {
        sc.switchScene(event, sc.MENU_DISPLAY);
    }

    @FXML
    private void exit(MouseEvent event) {
        Stage stage = (Stage) label.getScene().getWindow();
        stage.close();
    }

}
