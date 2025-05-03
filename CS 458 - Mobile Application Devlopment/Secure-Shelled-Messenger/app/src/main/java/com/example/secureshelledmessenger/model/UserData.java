/**
 * @author Caleb Parten
 * @author David Schmith
 * @author Mario Soto
 * @date 12/10/2024
 *
 * This file defines the model for the user. It is a singleton object that is created when a
 * user logs in. This is used throughout the application to use user information.
 */
package com.example.secureshelledmessenger.model;

import android.content.Context;

import java.util.ArrayList;

public class UserData {

    private static UserData userData;
    private String username;
    private String password;
    private Long userid;
    private ArrayList<Contact> contacts;


    private UserData(Context context){

    }

    public static UserData getInstance(Context context){
        if(userData == null){
            userData = new UserData(context);
        }
        return userData;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

}
