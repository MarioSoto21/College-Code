/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab6;

/* @author sotoM
 */

public class Student {
	// Instance variables
    private String name;
    private int age;
    
       public Student(String name, int age) {
        this.name = "";
        this.age = age;
    }

    /**
     * Sets the name for the student
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }
    
    
    /*
     Gets the student's name
     @return the student's name
     */
    public String getName()
    {
        return name;
    }
    //Sets the age for the student
    public void setAge(int age) {
        this.age = age;
    }
    public int setAge()
    {
        return age;
    }
}
