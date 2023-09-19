/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import controller.ManagerStudent;
import view.Menu;

/**
 *
 * @author This PC
 */
public class Main {
    public static void main(String[] args) {        
        Menu<String> menu= new ManagerStudent();
        menu.run();
    }
}
