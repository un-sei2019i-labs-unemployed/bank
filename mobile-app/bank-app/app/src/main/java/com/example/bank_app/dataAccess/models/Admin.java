package com.example.bank_app.dataAccess.models;

public class Admin {

    private String nombre;
    private int password;

    public Admin(String nombre, int password) {
        this.nombre = nombre;
        this.password = password;
    }

    public Admin(int password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }
}
