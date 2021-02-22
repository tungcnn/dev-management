/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devlv.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author SonTung
 */
public class AlertDisplayer {

    public final int WARNING = 1;
    public final int INFORMATION = 2;

    private AlertDisplayer() {

    }

    public static AlertDisplayer getInstance() {
        return AlertHelper.INSTANCE;
    }

    private static class AlertHelper {

        private static final AlertDisplayer INSTANCE = new AlertDisplayer();
    }

    public void display(String str, int type) {
        Alert a;
        if (type == 1) {
            a = new Alert(Alert.AlertType.WARNING);
        } else {
            a = new Alert(Alert.AlertType.INFORMATION);
        }

        Label lb = new Label(str);
        lb.setFont(Font.font("Arial", 16));
        a.getDialogPane().setContent(lb);
        a.show();
    }
}
