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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
    SceneChanger sc = SceneChanger.getInstance();
    AlertDisplayer ad = AlertDisplayer.getInstance();
    String notFoundStr = "Không tìm thấy nhân viên với ID này";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshTable();
    }

    @FXML
    private void backMenu(MouseEvent event) throws IOException {
        sc.switchScene(event, sc.MENU);
    }

    @FXML
    private void edit(MouseEvent event) throws IOException {
        Dev dev = doSearch();
        Stage stage = (Stage) table.getScene().getWindow();
        if (dev != null) {
            sc.switchScene(event, sc.MENU_EDIT);
            stage.setUserData(dev);
        } else {
            ad.display(notFoundStr, ad.WARNING);
        }
    }

    @FXML
    private void remove(MouseEvent event) {
        Dev dev = doSearch();
        if (dev != null) {
            DevManagement.remove(dev);
            DevManagement.writeToFile();
            refreshTable();
            ad.display("Nhân viên " + dev.getName() + " đã được nghỉ việc.", ad.INFORMATION);
            searchField.setText("");
        } else {
            ad.display(notFoundStr, ad.WARNING);
        }
    }

    private Dev doSearch() {
        String id = searchField.getText();
        return DevManagement.search(id);
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
