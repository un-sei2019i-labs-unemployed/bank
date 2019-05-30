package com.example.bank_app.dataAccess.models;

public class User {

    private int personal_id;
    private String name;
    private int password;

    public User(int personal_id, String name, int password) {
        this.personal_id = personal_id;
        this.name = name;
        this.password = password;
    }

    public User(String name, int password) {
        this.name = name;
        this.password = password;
    }

    public int getPersonal_id() {
        return personal_id;
    }

    public void setPersonal_id(int personal_id) {
        this.personal_id = personal_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }
}
