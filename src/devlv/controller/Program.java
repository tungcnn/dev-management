package devlv.controller;

import devlv.entities.Dev;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
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
        initializeDataBase();
    }

    public static void main(String[] args) {
        launch(args);
    }

    void initializeDataBase() throws FileNotFoundException {
        File file = new File("devs.txt");
        Scanner reader = new Scanner(file);
        while (reader.hasNextLine()) {
            String[] str = reader.nextLine().split(",");
            String name = str[0];
            String date = str[1];
            String id = str[2];
            double score = Double.parseDouble(str[3]);
            String position = str[4];
            Dev dev = DevFactory.getDev(position);
            dev.setName(name);
            dev.setDate(date);
            dev.setId(id);
            dev.setScore(score);
            DevManagement.devs.add(dev);
        }
    }
}
