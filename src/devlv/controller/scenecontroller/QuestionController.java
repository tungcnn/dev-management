package devlv.controller.scenecontroller;

import devlv.controller.DevManagement;
import devlv.entities.Dev;
import java.io.*;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class QuestionController implements Initializable {

    @FXML
    private ToggleGroup q1;
    @FXML
    private TabPane tabPane;
    @FXML
    private ToggleGroup q2;
    @FXML
    private ToggleGroup q3;
    @FXML
    private ToggleGroup q4;
    @FXML
    private ToggleGroup q5;
    @FXML
    private ToggleGroup q6;
    @FXML
    private ToggleGroup q7;
    @FXML
    private ToggleGroup q8;
    @FXML
    private ToggleGroup q9;
    @FXML
    private ToggleGroup q10;
    @FXML
    private ToggleGroup q11;
    @FXML
    private ToggleGroup q12;
    @FXML
    private ToggleGroup q13;
    @FXML
    private ToggleGroup q14;
    @FXML
    private ToggleGroup q15;
    @FXML
    private ToggleGroup q16;
    @FXML
    private ToggleGroup q17;
    @FXML
    private ToggleGroup q18;
    @FXML
    private ToggleGroup q19;
    @FXML
    private ToggleGroup q20;
    @FXML
    private ToggleGroup q21;
    @FXML
    private ToggleGroup q22;
    @FXML
    private ToggleGroup q23;
    @FXML
    private ToggleGroup q24;
    @FXML
    private ToggleGroup q25;
    @FXML
    private ToggleGroup q26;
    @FXML
    private ToggleGroup q27;
    @FXML
    private ToggleGroup q28;
    @FXML
    private Button submitBtn;
    @FXML
    private Button backBtn;
    @FXML
    private Button nextBtn;
    @FXML
    private Label timer;
    @FXML
    private Button startBtn;

    private static int counter = 180;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        timeline.setCycleCount(6);
        timeline.play();
        timeline.setOnFinished(event->{
            try {
                backMenu();
            } catch (IOException ex) {
                Logger.getLogger(QuestionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @FXML
    private void handleSubmit(ActionEvent event) throws IOException {
        boolean done = true;
        Stage stage = (Stage) submitBtn.getScene().getWindow();
        ToggleGroup[] list = {q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, q11, q12, q13, q14, q15, q16, q17, q18, q19, q20, q21, q22, q23, q24, q25, q26, q27, q28};
        double sum = 0;
        for (ToggleGroup tg : list) {
            int score = tg.getToggles().indexOf(tg.getSelectedToggle());
            if (score == -1) {
                done = false;
            }
            sum += score;
        }

        if (!done) {
            showWarning();
        } else {
            boolean pass = false;
            Dev dev = (Dev) stage.getUserData();
            String position = dev.getClass().getSimpleName();
            double average = sum / 28;
            average = Math.round(average * 100.0) / 100.0;
            dev.setScore(average);
            boolean passLeader = "TeamLeader".equals(position) && average >= 2.3;
            boolean passSenior = "Senior".equals(position) && average >= 1.5;
            boolean passJunior = "Junior".equals(position) && average >= 0.7;
            boolean passIntern = "Intern".equals(position) && average >= 0.3;
            if (passLeader || passSenior || passJunior || passIntern) {
                showInfo(dev);
            } else {
                showFail(average);
                DevManagement.remove(dev);
            }
            backMenu();
            DevManagement.writeToFile();
        }
    }

    @FXML
    private void handleBack(ActionEvent event) {
        tabPane.getSelectionModel().selectPrevious();
    }

    @FXML
    private void handleNext(ActionEvent event) {
        tabPane.getSelectionModel().selectNext();
    }

    private void showInfo(Dev dev) {
        DecimalFormat df2 = new DecimalFormat("#.##");
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        Text text = new Text("Đã thêm nhân viên " + dev.getName() + " sinh ngày " + dev.getDate() + " với ID " + dev.getId()
                + "\nĐiểm số: " + dev.getScore() + ", vị trí trong công ty: " + dev.getClass().getSimpleName());
        text.setFont(Font.font("Arial", 16));
        text.setWrappingWidth(400);
        a.getDialogPane().setContent(text);
        a.show();
    }

    private void showWarning() {
        Alert a = new Alert(Alert.AlertType.WARNING);
        Text text = new Text("Chưa trả lời hết câu hỏi!");
        text.setFont(Font.font("Arial", 16));
        text.setWrappingWidth(400);
        a.getDialogPane().setContent(text);
        a.show();
    }

    private void showFail(double score) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        Text text = new Text("Ứng viên không đạt điểm tối thiểu! (" + score + ")");
        text.setFont(Font.font("Arial", 16));
        text.setWrappingWidth(400);
        a.getDialogPane().setContent(text);
        a.show();
    }

    private void backMenu() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/devlv/view/Menu.fxml"));
        Scene scene = new Scene(root, 600, 400);
        Stage stage = (Stage) tabPane.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    private void handleStart(ActionEvent event) {

    }
    final Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1),event->{
        timer.setText(String.valueOf(counter));
        counter--;
    }));
}
