package com.example.bank_app.logic;

import android.content.Context;

import com.example.bank_app.dataAccess.models.User;
import com.example.bank_app.dataAccess.repositories.UsersRepository;

public class Signup {

    private UsersRepository provUser;

    public Signup(Context context) {
        this.provUser = new UsersRepository(context);
    }


    public String IsVerified(String fullname, String personal_id, String password) {
        if (!fullname.isEmpty()) {
            if (!personal_id.isEmpty() && personal_id.length()>=8) {
                if (!password.isEmpty() && password.length() == 6) {
                    int id = Integer.parseInt(personal_id);
                    int pass = Integer.parseInt(password);

                    User localProvUser = lookForUser(id);
                    // Handle
                    if (localProvUser != null) {
                        return "User Id Is Already Registered";
                    } else {
                        //hacer el registro en la base de datos;
                        if(provUser.insertUser(id,fullname,pass)){
                            return "User & Account Created";
                        }else{
                            return "Can´t Create User";
                        }
                    }

                } else {
                    return "Password Must Have 6 Numbers";
                }
            } else if(personal_id.isEmpty()) {
                return "Enter Personal ID";
            }else if(personal_id.length()<8){
                return "Personal ID Must Have 8 Numbers At Least";
            }else{
                return "Can´t Create User";
            }
        } else {
            return "Enter Name";
        }
    }

    private User lookForUser(int personal_id){
        provUser.createTable();
        provUser.open();
        if (provUser.readUser(personal_id)){
            provUser.close();
            return  provUser.getUser();
        }
        return null;
    }
}
