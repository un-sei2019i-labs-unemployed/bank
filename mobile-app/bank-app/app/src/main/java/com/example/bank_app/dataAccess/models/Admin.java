package com.example.bank_app.dataAccess.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Admin {

    @Id
    private String nombre;
    @Transient
    private int password;

    public Admin() {}

    public Admin(String nombre, int password) {
        this.nombre = nombre;
        this.password = password;
    }

    public Admin(int password) {
        this.password = password;
    }

    @Generated(hash = 1865026278)
    public Admin(String nombre) {
        this.nombre = nombre;
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
