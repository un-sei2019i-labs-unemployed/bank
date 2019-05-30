package com.example.bank_app.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bank_app.R;

import com.example.bank_app.logic.Login;
import com.example.bank_app.logic.Message;

public class LoginActivity extends AppCompatActivity {

    EditText _personal_id;
    EditText _password;
    Button _loginButton;
    TextView _singUp;
    Login login;
    Message message;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        _personal_id = findViewById(R.id.input_personalID);
        _password = findViewById(R.id.input_password);
        _loginButton = findViewById(R.id.buttonLogin);
        _singUp = findViewById(R.id.textViewCreateAccount);


        message = new Message();

        _loginButton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        dialog = message.waiting("Authenticating",LoginActivity.this);
                        login(_personal_id.getText().toString(),_password.getText().toString());
                    }
                });

        _loginButton.setEnabled(false);

        _singUp.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View viewIn) {
                        createAccount();
                    }
                });
    }

    private void login(String personal_id, String password){
        login = new Login(this);
        String msg = login.isAuthenticated(personal_id, password);
        dialog.dismiss();
        if(msg.equalsIgnoreCase("Connection successful")){
            menuUser();
        }else if(msg.equalsIgnoreCase("Enter Personal ID")){
            _personal_id.setError(msg);
        }else if(msg.equalsIgnoreCase("Password must have 6 numbers")){
            _password.setError(msg);
        }else{
            message.alert(msg,this);
        }
    }

    private void menuUser(){
        Intent userMenu = new Intent (getApplicationContext(), CreateAccountActivity.class);
        startActivity(userMenu);
    }

    private void createAccount(){
        Intent createAccount = new Intent (getApplicationContext(), CreateAccountActivity.class);
        startActivity(createAccount);
    }
}
