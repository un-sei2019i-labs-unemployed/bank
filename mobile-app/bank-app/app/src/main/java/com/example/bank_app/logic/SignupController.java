package com.example.bank_app.logic;

import android.content.Context;

import com.example.bank_app.dataAccess.models.*;
import com.example.bank_app.dataAccess.repositories.*;

public class SignupController {

    private UsersRepository provUser;
    private AccountsRepository provAccount;

    public SignupController(Context context) {
        this.provUser = new UsersRepository(context);
        this.provAccount = new AccountsRepository(context);
    }


    public String IsVerified(String fullname, String personal_id, String password) {
        if (!fullname.isEmpty()) {
            if (!personal_id.isEmpty() && personal_id.length()>=8) {
                if (!password.isEmpty() && password.length() == 6) {
                    int id = Integer.parseInt(personal_id);
                    int pass = Integer.parseInt(password);

                    User localProvUser = lookForUser(id);
                    lookForAccount();
                    // Handle
                    if (localProvUser != null) {
                        return "User Id Is Already Registered";
                    } else {
                        //hacer el registro en la base de datos;
                        if(provUser.insertUser(id,fullname,pass)){
                            int i=1;
                            while(i<provAccount.getAccountNumber()) {
                                if (provAccount.updateAccount(hashID(provUser.getUser(),i,provAccount.getAccountNumber()), provUser.getUser().getPersonal_id()))
                                    return "User & Account Created";
                                else
                                    i++;
                            }
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

    private int hashID(User user, int times, int size){
        int n=nextPrime(size);
        return (user.getPersonal_id()+user.getPassword()+user.getName().hashCode()+times-1)%n;
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

    private void lookForAccount(){
        provAccount.createTable();
        provAccount.open();
    }

    private boolean isPrime(int x)
    {
        for(int i=2;i<Math.sqrt(x);i++)
        {
            if(x%i == 0)
                return false;
        }
        return true;
    }

    private int nextPrime(int x)
    {
        while(!isPrime(x))
            x++;

        return x;
    }
}
