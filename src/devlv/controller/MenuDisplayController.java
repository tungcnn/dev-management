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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SonTung
 */
public class MenuDisplayController implements Initializable {

    @FXML
    private TableView<Dev> table;
    @FXML
    private TableColumn<Dev, Integer> id;
    @FXML
    private TableColumn<Dev, String> name;
    @FXML
    private TableColumn<Dev, String> date;
    @FXML
    private TableColumn<Dev, Double> score;
    @FXML
    private TableColumn<Dev, String> position;
    @FXML
    private TextField searchField;

    ObservableList<Dev> list = FXCollections.observableArrayList();
    Stage stage;
    Parent root;
    Scene scene;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshTable();
    }

    @FXML
    private void backMenu(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/devlv/view/Menu.fxml"));
        scene = new Scene(root, 600, 400);
        stage = (Stage) table.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    private void edit(MouseEvent event) throws IOException {
        Dev dev = doSearch();

        if (dev != null) {
            root = FXMLLoader.load(getClass().getResource("/devlv/view/MenuEdit.fxml"));
            scene = new Scene(root, 600, 400);
            stage = (Stage) table.getScene().getWindow();
            stage.setScene(scene);
            stage.setUserData(dev);
        } else {
            showNotFound();
        }
    }

    @FXML
    private void remove(MouseEvent event) {
        Dev dev = doSearch();
        if (dev != null) {
            DevManagement.remove(dev);
            DevManagement.writeToFile();
            refreshTable();
            showRemoveConfirmation(dev.getName());
            searchField.setText("");
        } else {
            showNotFound();
        }
    }

    private Dev doSearch() {
        String id = searchField.getText();
        return DevManagement.search(id);
    }

    private void showNotFound() {
        Alert a = new Alert(Alert.AlertType.WARNING);
        Text text = new Text("Không tìm thấy nhân viên với ID này");
        text.setFont(Font.font("Arial", 16));
        text.setWrappingWidth(300);
        a.getDialogPane().setContent(text);
        a.show();
    }

    private void showRemoveConfirmation(String name) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        Text text = new Text("Nhân viên " + name + " đã nghỉ việc.");
        text.setFont(Font.font("Arial", 16));
        text.setWrappingWidth(300);
        a.getDialogPane().setContent(text);
        a.show();
    }

    private void refreshTable() {
        table.getItems().clear();
        for (Dev dev : DevManagement.devs) {
            table.getItems().add(dev);
        }
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        score.setCellValueFactory(new PropertyValueFactory<>("score"));
        position.setCellValueFactory(new PropertyValueFactory<>("position"));
    }
}
