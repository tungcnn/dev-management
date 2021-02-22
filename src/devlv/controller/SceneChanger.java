/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devlv.controller;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author SonTung
 */
public class SceneChanger {

    final public String MENU = "/devlv/view/Menu.fxml";
    final public String MENU_ADD = "/devlv/view/MenuAdd.fxml";
    final public String MENU_DISPLAY = "/devlv/view/MenuDisplay.fxml";
    final public String MENU_EDIT = "/devlv/view/MenuEdit.fxml";
    final public String QUESTION = "/devlv/view/Question.fxml";

    private SceneChanger() {

    }

    public static SceneChanger getInstance() {
        return SceneChangerHelper.INSTANCE;
    }

    private static class SceneChangerHelper {

        private static final SceneChanger INSTANCE = new SceneChanger();
    }

    public void switchScene(MouseEvent event, String path) throws IOException {
        double screenSpace = Screen.getPrimary().getBounds().getMaxX() - 1200;
        Parent root = FXMLLoader.load(getClass().getResource(path));
        Scene scene;
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        if (path.equals(QUESTION)) {
            scene = new Scene(root, 1200, 720);
            stage.setX(screenSpace/2);
            stage.setY(30);
        } else {
            scene = new Scene(root, 600, 400);
        }
        stage.setScene(scene);
    }
}
