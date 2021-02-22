/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devlv.controller;

import devlv.entities.Dev;
import devlv.entities.Intern;
import devlv.entities.Junior;
import devlv.entities.Senior;
import devlv.entities.TeamLeader;

/**
 *
 * @author SonTung
 */
public class DevFactory {
    static public Dev getDev(String type) {
        switch(type) {
            case "Intern":
                return new Intern();
            case "Junior":
                return new Junior();
            case "Senior":
                return new Senior();
            case "TeamLeader":
                return new TeamLeader();
            default:
                return null;
        }
    }
}
