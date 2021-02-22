/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devlv.entities;

import java.io.Serializable;

/**
 *
 * @author SonTung
 */
public class Dev implements Serializable{

    protected String name;
    protected String date;
    protected String id;
    protected double score;
    protected double salary;
    protected double baseSalary;
    protected double rate;
    protected String position = this.getClass().getSimpleName();

    public Dev() {

    }

    public Dev(String name, String date, String id) {
        this.name = name;
        this.date = date;
        this.id = id;
    }

    public Dev(String name, String date, String id, double score) {
        this.name = name;
        this.date = date;
        this.id = id;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getSalary() {
        return baseSalary * rate;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public double getRate() {
        return rate;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return name
                + ", ngày sinh: " + date
                + ", id: " + id
                + "\nĐiểm bài test: " + score
                + ", lương cơ bản: " + baseSalary/1000000 + " triệu"
                + ", hệ số lương: " + rate
                + "\nVị trí trong công ty: " + position;
    }
}
