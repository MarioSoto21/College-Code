/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab6;

/**
 *
 * @author sotoM
 */
import java.util.ArrayList;
public class School {
private ArrayList<Course> Courses;

public School(){
    this.Courses = new ArrayList<>();
}
public void addCourse(Course course) {
    this.Courses.add(course);
}
public int getNumCourses(){
 int numCourses = Courses.size();
       return numCourses;
}
public void getCourseInfo(int courseId) {
    if(Course.getCourseID(courseID)){
       Course Course =Courses.get(courseID);
        System.out.println("The course"+Course.getName()+"has");
        System.out.println(+Course.getNumStudents()+" students");
    }
    else
        System.out.println("The course"+courseID+"does not exist");
    }
}
