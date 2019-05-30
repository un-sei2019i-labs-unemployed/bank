package com.example.bank_app.logic;

import android.content.Context;

import com.example.bank_app.dataAccess.models.User;
import com.example.bank_app.dataAccess.repositories.UsersRepository;

public class Login {

    private UsersRepository provUser;

    public Login(Context context) {
        this.provUser = new UsersRepository(context);
    }

    private User login(int personal_id){
        provUser.createTable();
        provUser.open();
        if (provUser.readUser(personal_id)){
            provUser.close();
            return  provUser.getUser();
        }
        return null;
    }

    public String isAuthenticated(String personal_id, String password){
        if(!personal_id.isEmpty()){
            if(!password.isEmpty() && password.length()==6){
                int id = Integer.parseInt(personal_id);
                int pass = Integer.parseInt(password);
                User provUser = login(id);
                // Handle
                if (provUser != null){
                    if(provUser.getPassword() == pass){
                        return "Connection successful";
                    }
                    return "Incorrect password";
                }
                return "User doesn't exist";
            }else{
                return "Password must have 6 numbers";
            }
        }else{
            return "Enter Personal ID";
        }




    }
}
