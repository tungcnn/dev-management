/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devlv.controller;

import devlv.entities.Dev;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author SonTung
 */
public class ReadWriteFile {

    public static void write(ArrayList<Dev> rows) {
        try ( BufferedWriter bw = new BufferedWriter(new FileWriter("devs.csv"))) {
            bw.write("Name");
            bw.write(",");
            bw.write("DateOfBirth");
            bw.write(",");
            bw.write("ID");
            bw.write(",");
            bw.write("Score");
            bw.write(",");
            bw.write("BaseSalary");
            bw.write(",");
            bw.write("Rate");
            bw.write(",");
            bw.write("Position");
            bw.newLine();
            for (Dev rowData : rows) {
                bw.write(rowData.toCSVFormat());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read() {
        try {
            File file = new File("devs.csv");
            if (!file.isFile()) {
                file.createNewFile();
            } else {
                BufferedReader bir = new BufferedReader(new FileReader("devs.csv"));
                String row = bir.readLine();
                row = bir.readLine();
                while (row != null) {
                    String[] data = row.split(",");
                    String position = data[6];
                    Dev c = DevFactory.getDev(position);
                    c.setName(data[0]);
                    c.setDate(data[1]);
                    c.setId(data[2]);
                    c.setScore(Double.parseDouble(data[3]));
                    DevManagement.devs.add(c);
                    row = bir.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
