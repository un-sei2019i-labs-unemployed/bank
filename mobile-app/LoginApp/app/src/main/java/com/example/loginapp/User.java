package com.example.loginapp;


public class User {
    private int id;
    private String userName;
    //private String email;
    private int password;

    public User(int id, String userName, /*String email,*/ int password) {
        this.id = id;
        this.userName = userName;
        //this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /*public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }*/

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }
}