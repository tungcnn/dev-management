/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devlv.entities;

/**
 *
 * @author SonTung
 */
public class Intern extends Dev{
    public Intern() {
        this.baseSalary = 1000000;
        this.rate = 1;
    }
    @Override
    public String toString() {
        return "Dev{" + "name=" + name + ", date=" + date + ", id=" + id + ", score=" + score + ", baseSalary=" + baseSalary + ", rate=" + rate + ", position=" + position + '}';
    }
    
}
