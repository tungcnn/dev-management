/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devlv.controller;

import devlv.entities.Dev;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SonTung
 */
public class DevManagement {

    static ArrayList<Dev> devs = new ArrayList<>();

    static void add(Dev dev) {
        devs.add(dev);
        writeToFile();
    }

    static public void remove(Dev dev) {
        devs.remove(dev);
        writeToFile();
    }

    static public void update() {
        writeToFile();
    }
    static public Dev search(String id) {
        for (Dev dev : devs) {
            if (dev.getId().equals(id)) {
                return dev;
            }
        }
        return null;
    }
    static public void writeToFile() {
        try {
            File myFile = new File("devs.txt");
            if (!myFile.isFile()) {
                myFile.createNewFile();
            }
            FileWriter myWriter = new FileWriter("devs.txt");
            for (Dev dev : devs) {
                myWriter.write(dev.getName() + "," + dev.getDate() + "," + String.valueOf(dev.getId()) + "," + String.valueOf(dev.getScore()) + "," + dev.getClass().getSimpleName() + "\n");
            }
            myWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(QuestionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
