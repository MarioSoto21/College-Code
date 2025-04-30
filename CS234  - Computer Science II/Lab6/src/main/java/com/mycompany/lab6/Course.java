/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab6;

import java.util.ArrayList;

/**
 *
 * @author sotoM
 */
public class Course {

    private int courseID;
    private String name;
    private ArrayList<Student> Students;
    private Instructor Professor;
    
    public Course(int courseID, String name) {
        this.Students = new ArrayList<>();
        this.courseID = courseID;
        this.name = name;
    }
    public Course(int courseID, String name, Instructor Professor){
        this.Students = new ArrayList<>();
        this.courseID = courseID;
        this.name = name;
        this.Professor = Professor;
    }
    public int getCourseID() {
        return courseID;
}
    public String getName() {
        return name;
    }
    public Instructor getProfessor() {
        return Professor;
    }
    public void setCourseID(int courseID){
        this.courseID = courseID;
    }
    public void setCourse(Instructor Professor) {
        this.Professor = Professor;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setProfessor(Instructor Professor) {
        this.Professor = Professor;
    }
    public int getNumStudents(){
       return Students.size();
    }
    public void addStudent(Student student){
        Students.add(student);
} 
}
