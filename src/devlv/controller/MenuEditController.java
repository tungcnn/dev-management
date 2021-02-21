/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devlv.controller;

import devlv.entities.Dev;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    Parent root;
    Scene scene;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleCancel(MouseEvent event) throws IOException {
        back();
    }

    private void back() throws IOException {
        root = FXMLLoader.load(getClass().getResource("/devlv/view/MenuDisplay.fxml"));
        scene = new Scene(root, 600, 400);
        stage = (Stage) name.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    private void handleSave(MouseEvent event) throws IOException {
        try {
            saveInfo();
            back();
            DevManagement.writeToFile();
        } catch (NullPointerException e) {
            showEmptyWarning();
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
            root = FXMLLoader.load(getClass().getResource("/devlv/view/Question.fxml"));
            scene = new Scene(root, 1200, 720);
            stage = (Stage) name.getScene().getWindow();
            stage.setScene(scene);
            stage.setX(0);
            stage.setY(0);
        } catch (NullPointerException e) {
            showEmptyWarning();
        }
    }

    private void showEmptyWarning() {
        Alert a = new Alert(Alert.AlertType.WARNING);
        Text text = new Text("Hãy điền thông tin mới, hoặc bấm nút 'Show' để tự điền thông tin cũ.");
        text.setFont(Font.font("Arial", 16));
        text.setWrappingWidth(300);
        a.getDialogPane().setContent(text);
        a.show();
    }
}
