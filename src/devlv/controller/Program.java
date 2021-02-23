package devlv.controller;

import static devlv.controller.DevManagement.*;
import devlv.controller.scenecontroller.QuestionController;
import devlv.entities.Dev;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Program extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/devlv/view/Menu.fxml"));
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Google employee management!");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
        ReadWriteFile.read();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
