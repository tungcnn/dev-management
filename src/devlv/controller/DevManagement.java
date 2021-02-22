/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devlv.controller;

import devlv.entities.Dev;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author SonTung
 */
public class DevManagement {

    final static public String PATH = "devs.dat";
    static public ArrayList<Dev> devs = new ArrayList<>();

    static public void add(Dev dev) {
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
    static public boolean checkID (String id) {
        for (Dev dev : devs) {
            if (dev.getId().equals(id)) return false;
        }
        return true;
    }
    static public void writeToFile() {
        ReadWriteFile.writeObjectToFile(devs, PATH);
    }
}
