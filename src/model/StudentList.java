/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import view.Menu;
import view.Validation;

/**
 *
 * @author This PC
 */
public class StudentList {
    private ArrayList<Student> list;
    private ArrayList<CourseOfStudent> list_cs;
    private Validation validation;
    
    public StudentList(){
        this.list= new ArrayList<>();
        this.list_cs= new ArrayList<>();
        this.validation= new Validation();
    }
    
    public void createStudent(){       
        while(true){
            System.out.println("Enter ID Student: ");
            String id= validation.checkInputString();
            if(!validation.checkIdExist(list, id)){
                System.out.println("Enter Student Name: ");
                String name= validation.checkInputString();
                System.out.println("Enter Semester: ");
                int semester= validation.checkInputInt();
                System.out.println("Enter Course Name: ");
                String courseName= validation.checkInputString();
                Student student= new Student(id, name, semester, courseName);
                list.add(student);
                list_cs.add(new CourseOfStudent(id, semester, courseName));
            }else{
                System.out.println("Enter Semester: ");
                int semester= validation.checkInputInt();
                System.out.println("Enter Course Name: ");
                String courseName= validation.checkInputString();
                list_cs.add(new CourseOfStudent(id, semester, courseName));
            }                      
            if(list.size()<2){
                continue;
            }
            System.out.println("Do you want to continue (Y/N)? Choose Y to continue, N to return main screen");
            if(!validation.checkInputYesNo()){
                return;
            }
        }
    }
    
    public void findSort(){
        if(list.isEmpty()){
            System.err.println("List Student is empty!");
            return;
        }
        System.out.println("Please enter name or a part of student name searching: ");
        String name= validation.checkInputString();
        ArrayList<Student> list_find= new ArrayList<>();
        for (Student student : list) {
            if(student.getName().contains(name)){
                list_find.add(student);
            }
        }
        if(list_find.isEmpty()){
            System.out.println("No found student!");
        }else{
            Collections.sort(list_find, (o1, o2) -> o1.getName().compareTo(o2.getName() ) );
            displayListSearch(list_find);
        }             
    }
    
    public void updateAndDelete(){
        if(list==null){
            System.err.println("List Student is empty!");
            return; 
        }
        System.out.println("Please enter ID want to update or delete: ");
        String id= validation.checkInputString();
        Student list_find= findListByID(id);
        ArrayList<CourseOfStudent> course_findId= findCourseByID(id);
        if(list_find==null){
            System.out.println("ID no exist!");
        }else{
            System.out.println("Do you want to update (U) or delete (D) student?");
            if(validation.checkInputUpdateDelete()){
                updateStudent(list_find, course_findId);
            }else{
                list.remove(list_find);
                for (CourseOfStudent courseOfStudent : course_findId) {
                    list_cs.remove(courseOfStudent);
                }
            }
        }        
    }
    
    public Student findListByID(String id){
        Student student_find=null;
        for (Student student : list) {
            if(student.getId().equalsIgnoreCase(id)){
                student_find= student;
            }
        }
        return student_find;
    }
    
    public ArrayList<CourseOfStudent> findCourseByID(String id){
        ArrayList<CourseOfStudent> course_find= new ArrayList<>();
        for (CourseOfStudent course : list_cs) {
            if(course.getId().equalsIgnoreCase(id)){
                course_find.add(course);
            }
        }
        return course_find;
    }
    
    
    
    public void updateStudent(Student student, ArrayList<CourseOfStudent> course_list){
        String title= "Updated Student";
        String[] s= new String[] {"Update Name", "Update Semester and Course Name", "Exit"};
        Menu menu= new Menu(title, s) {
            @Override
            public void execute(int n) {
                switch (n) {
                    case 1:
                        System.out.println("Enter name update: ");
                        String name= validation.checkInputString();
                        student.setName(name);                   
                        break;
                    case 2:
                        for (int i = 0; i < course_list.size(); i++) {
                            System.out.println((i+1)+". "+course_list);
                        }
                        System.out.println("Please choice to update: ");
                        int choice= validation.checkInputIntLimit(1, course_list.size());                
                        System.out.println("Enter semester update: ");
                        int semester= validation.checkInputInt();
                        student.setSemester(semester);
                        course_list.get(choice-1).setSemester(semester);
                        System.out.println("Enter  course name update: ");
                        String courseName= validation.checkInputString();
                        student.setCourseName(courseName);
                        course_list.get(choice-1).setCourseName(courseName);          
                        break;                                          
                }
            }
        };
        menu.run();
    }
    
    public void report(){
        if(list==null){
            System.err.println("List empty");
            return;
        }        
        ArrayList<Report> list_rp= new ArrayList<>();        
        for (CourseOfStudent course : list_cs) {            
            for (Student student : list) {               
                Report report_find= findListReportByID(student.getId(), list_rp);
                if(course.getId().equals(student.getId())){
                    System.out.println(student.getName()+" - "+course.getCourseName());
                    if(report_find==null){
                        list_rp.add(new Report(student.getId(),student.getName(),course.getCourseName(), 1));
                    }else{
                        report_find.setTotalCourse(report_find.getTotalCourse()+1);
                        report_find.setCourseName(report_find.getCourseName()+", "+course.getCourseName());
                    }
                    break;
                }
            }
        }
        System.out.println("The report as below: ");
        for (Report report : list_rp) {
            System.out.println(report.getName()+" | "+report.getCourseName()+" | "+report.getTotalCourse());           
        }
               
    }
    
    public Report findListReportByID(String id, ArrayList<Report> list_rp){
        Report report_find=null;
        for (Report report : list_rp) {
            if(report.getId().equalsIgnoreCase(id)){
                report_find= report;
                break;
            }
        }
        return report_find;
    }
    
    public void displayListSearch(ArrayList<Student> list_student){
        for (Student student : list_student) {
            System.out.println(student);
        }
    }
    
    public void displayList(){
        for (Student student : list) {
            System.out.println(student);
        }
    }
}
