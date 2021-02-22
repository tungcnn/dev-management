/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devlv.controller.scenecontroller;

import devlv.controller.AlertDisplayer;
import devlv.controller.DevManagement;
import devlv.controller.SceneChanger;
import devlv.entities.Dev;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SonTung
 */
public class MenuEditController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField id;
    @FXML
    private TextField date;

    Stage stage;
    SceneChanger sc = SceneChanger.getInstance();
    AlertDisplayer ad = AlertDisplayer.getInstance();
    String emptyStr = "Hãy điền thông tin mới, hoặc bấm nút 'Show' để tự điền thông tin cũ.";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleCancel(MouseEvent event) throws IOException {
        sc.switchScene(event, sc.MENU_DISPLAY);
    }


    @FXML
    private void handleSave(MouseEvent event) throws IOException {
        try {
            saveInfo();
            sc.switchScene(event, sc.MENU_DISPLAY);
            DevManagement.writeToFile();
        } catch (NullPointerException e) {
            ad.display(emptyStr, ad.WARNING);
        }
    }

    private void saveInfo() {
        Dev dev = (Dev) stage.getUserData();
        dev.setName(name.getText());
        dev.setDate(date.getText());
        dev.setId(id.getText());
    }

    @FXML
    private void handleShowInfo(MouseEvent event) {
        stage = (Stage) name.getScene().getWindow();
        Dev dev = (Dev) stage.getUserData();
        name.setText(dev.getName());
        date.setText(dev.getDate());
        id.setText(String.valueOf(dev.getId()));
    }

    @FXML
    private void handleTest(MouseEvent event) throws IOException {
        try {
            saveInfo();
            sc.switchScene(event, sc.QUESTION);
        } catch (NullPointerException e) {
            ad.display(emptyStr, ad.WARNING);
        }
    }
}
