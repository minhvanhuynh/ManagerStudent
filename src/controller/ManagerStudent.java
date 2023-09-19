/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.StudentList;
import view.Menu;

/**
 *
 * @author This PC
 */
public class ManagerStudent  extends Menu<String>{
        
    private StudentList list;
    static String title= "WELCOME TO STUDNET MANAGEMENT";
    static String[] s= new String[] {"Create", "Find and Sort", "Update/Delete", "Report", "Exit"};   
    
    public ManagerStudent(){
        super(title, s);
        list= new StudentList();
    }
    
    @Override
    public void execute(int n) {
        switch (n) {
            case 1:
                list.createStudent();
                break;
            case 2:
                list.findSort();
                break;
            case 3:
                list.updateAndDelete();
                break;
            case 4:
                list.report();
                break;
            case 5:
                System.exit(0);
        }
    }
    
}
