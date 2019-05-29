package com.example.loginapp;


public class User {
    public int id;
    public String userName;
    public String email;
    public int password;

    public User(int id, String userName, String email, int password) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

}