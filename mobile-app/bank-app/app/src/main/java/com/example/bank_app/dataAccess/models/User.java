package com.example.bank_app.dataAccess.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class User {

    @Id
    private int personal_id;

    @Transient
    private String name;

    @Transient
    private int password;

    public User() {}

    public User(int personal_id, String name, int password) {
        this.personal_id = personal_id;
        this.name = name;
        this.password = password;
    }


    public User(String name, int password) {
        this.name = name;
        this.password = password;
    }

    @Generated(hash = 1405246929)
    public User(int personal_id) {
        this.personal_id = personal_id;
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
