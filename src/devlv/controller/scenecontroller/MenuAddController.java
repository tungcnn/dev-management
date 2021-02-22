/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devlv.controller.scenecontroller;

import devlv.controller.AlertDisplayer;
import devlv.controller.DevFactory;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
    private ComboBox<String> position;

    Stage stage;
    SceneChanger sc = SceneChanger.getInstance();
    AlertDisplayer ad = AlertDisplayer.getInstance();
    String emptyWarning = "Chưa điền đầy đủ thông tin!";

    String guide = """
                    Ứng viên sẽ phải trả lời 28 câu hỏi phỏng vấn theo Ma Trận Năng Lực của Sijin Joseph. 
                    Các câu hỏi được chia thành 4 nhóm: Computer Science, Software Engineering, Programming, Knowledge. 
                    Điểm trung bình của các câu hỏi sẽ được đánh giá theo Big O notation
                    Level của Dev từ thấp đến cao là 2n (level 0), n2 (level 1), n (level 2), log((n) (level 3)
                    """;

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
        sc.switchScene(event, sc.MENU);
    }

    @FXML
    private void handleInterview(MouseEvent event) throws IOException {
        try {
            Dev dev = createDev();
            DevManagement.add(dev);
            ad.display(guide, ad.INFORMATION);
            sc.switchScene(event, sc.QUESTION);
        } catch (NullPointerException e) {
            ad.display(emptyWarning, ad.WARNING);
        } catch (Exception e) {
            ad.display("Mã ID này đã tồn tại!", ad.WARNING);
        }
    }

    private Dev createDev() throws Exception {
        String id = idField.getText();
        if (!DevManagement.checkID(id)) {
            throw new Exception();
        } else {
            String appliedPosition = position.getSelectionModel().getSelectedItem();
            Dev dev = DevFactory.getDev(appliedPosition);
            dev.setName(nameField.getText());
            dev.setDate(dateField.getText());
            dev.setId(idField.getText());
            return dev;
        }
    }

    @FXML
    private void handleSkip(MouseEvent event) throws IOException {
        try {
            Dev dev = createDev();
            DevManagement.add(dev);
            String confirmStr = "Đã thêm " + dev.toString();
            ad.display(confirmStr, ad.INFORMATION);
            sc.switchScene(event, sc.MENU);
        } catch (NullPointerException e) {
            ad.display(emptyWarning, ad.WARNING);
        } catch (Exception e) {
            ad.display("Mã ID này đã tồn tại!", ad.WARNING);
            idField.clear();
        }
    }
}
