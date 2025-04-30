/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab6;

/**
 *
 * @author sotoM
 */
public class Instructor {
    private String name;
    private String location;
public Instructor(){
    this.name = "STAFF";
    this.location = "Portales";
}
public Instructor(String name, String location) {
    this.name = name;
    this.location = location;
}
public String getName(){
    return name;
}
public String getLocation(){
    return location;
}
public void setName(String name){
    this.name = name;
}
public void setLocation(String location) {
    this.location = location;
}
}
