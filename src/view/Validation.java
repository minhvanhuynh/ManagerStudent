/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import model.Student;


/**
 *
 * @author This PC
 */
public class Validation {
    private final static Scanner sc= new Scanner(System.in);
    
    public int checkInputIntLimit(int min, int max){
        while(true){
            try{
                int check= Integer.parseInt(sc.nextLine().trim());
                if(check < min || check > max)
                    throw new Exception();
                else return check;             
            }catch(Exception e){
                System.out.println("Please input number in range ["+min+", "+max+"]!");
                System.out.println("Enter again: ");
            }
        }
    }
    
    public String checkInputString() {
        while (true) {
            String string = sc.nextLine().trim();
            if (string.isEmpty()) {
                System.out.println("Not allowed empty!");
                System.out.println("Enter again: ");
            } else {
                return string;
            }
        }
    }
    
    public int checkInputInt(){
        while(true){
            String s= checkInputString();
            try{
                int number= Integer.parseInt(s);
                return number;
            }catch(Exception e){
                System.out.println("Just enter number!");
                continue;
            }
        }
    }
    
    public boolean checkInputYesNo(){
        while(true){
            String result= checkInputString();
            if(result.equalsIgnoreCase("Y")){
                return true;
            }else if(result.equalsIgnoreCase("N")){
                return false;
            }
            System.out.println("Please input y/Y or n/N.");
            System.out.println("Enter again: ");
        }   
    }
    
    public boolean checkInputUpdateDelete(){
        while(true){
            String result= checkInputString();
            if(result.equalsIgnoreCase("U")){
                return true;
            }else if(result.equalsIgnoreCase("D")){
                return false;
            }
            System.out.println("Please input y/Y or n/N.");
            System.out.println("Enter again: ");
        }   
    }
    
    public boolean checkIdExist(ArrayList<Student> list, String id){
        for (Student student : list) {
            if(student.getId().equalsIgnoreCase(id)){
                System.out.println("Id exist in list!");
                return true;
            }
        }
        return false;
    }
    
    
}