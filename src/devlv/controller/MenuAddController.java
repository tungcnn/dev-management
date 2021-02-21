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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
public class MenuAddController implements Initializable {

    @FXML
    private TextField nameField;
    @FXML
    private TextField idField;
    @FXML
    private TextField dateField;
    @FXML
    private Label label;
    @FXML
    private ComboBox<String> position;

    Parent root;
    Scene scene;
    Stage stage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList(
                "Intern",
                "Junior",
                "Senior",
                "TeamLeader"
        );
        position.setItems(list);
    }

    @FXML
    private void backMenu(MouseEvent event) throws IOException {
        backToMenu();
    }

    @FXML
    private void handleInterview(MouseEvent event) throws IOException {
        try {
            Dev dev = createDev();
            DevManagement.add(dev);
            showGuide();
            root = FXMLLoader.load(getClass().getResource("/devlv/view/Question.fxml"));
            scene = new Scene(root, 1200, 720);
            stage = (Stage) label.getScene().getWindow();
            stage.setUserData(dev);
            stage.setScene(scene);
            stage.setX(0);
            stage.setY(0);
        } catch (NullPointerException e) {
            showEmptyWarning();
        }
    }

    private Dev createDev() {
        String appliedPosition = position.getSelectionModel().getSelectedItem();

        Dev dev = DevFactory.getDev(appliedPosition);
        dev.setName(nameField.getText());
        dev.setDate(dateField.getText());
        dev.setId(idField.getText());
        return dev;
    }

    private void showGuide() {
        Alert a = new Alert(AlertType.INFORMATION);
        Text text = new Text("Ứng viên sẽ phải trả lời 28 câu hỏi phỏng vấn theo Ma Trận Năng Lực của Sijin Joseph. "
                + "Các câu hỏi được chia thành 4 nhóm: Computer Science, Software Engineering, Programming, Knowledge. "
                + "Điểm trung bình của các câu hỏi sẽ được đánh giá theo Big O notation, với level của Dev từ thấp đến cao là 2n (level 0), n2 (level 1), n (level 2), log((n) (level 3)");
        text.setFont(Font.font("Arial", 16));
        text.setWrappingWidth(500);
        a.setTitle("Hướng Dẫn Phỏng Vấn");
        a.getDialogPane().setContent(text);
        a.show();
    }

    private void showAddConfirm(Dev dev) {
        Alert a = new Alert(AlertType.INFORMATION);
        Text text = new Text("Đã thêm " + dev.toString());
        text.setFont(Font.font("Arial", 16));
        text.setWrappingWidth(500);
        a.getDialogPane().setContent(text);
        a.show();
    }

    @FXML
    private void handleSkip(MouseEvent event) throws IOException {
        try {
            Dev dev = createDev();
            DevManagement.add(dev);
            showAddConfirm(dev);
            backToMenu();
        } catch (NullPointerException e) {
            showEmptyWarning();
        }

    }

    private void showEmptyWarning() {
        Alert a = new Alert(AlertType.WARNING);
        Text text = new Text("Chưa điền đầy đủ thông tin!");
        text.setFont(Font.font("Arial", 16));
        text.setWrappingWidth(300);
        a.getDialogPane().setContent(text);
        a.show();
    }

    private void backToMenu() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/devlv/view/Menu.fxml"));
        Scene scene = new Scene(root, 600, 400);
        Stage stage = (Stage) label.getScene().getWindow();
        stage.setScene(scene);
    }
}
