package com.example.bank_app.data;

public class User {
    private int cedula;
    private String nombre;
    private int password;



    public User(int cedula, String nombre, int password) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.password = password;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
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
