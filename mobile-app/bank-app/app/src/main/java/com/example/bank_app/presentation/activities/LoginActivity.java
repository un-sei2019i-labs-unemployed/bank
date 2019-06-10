package com.example.bank_app.presentation.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.bank_app.dataAccess.models.User;

import com.example.bank_app.R;

import com.example.bank_app.businessLogic.controllers.LoginController;
import com.example.bank_app.businessLogic.Message;

public class LoginActivity extends AppCompatActivity {
    public static User logedUser;
    private EditText _personal_id;
    private EditText _password;

    private TextInputLayout _password_inputLayout;

    private Button _loginButton;

    private TextView _singUp;

    private LoginController login;

    private Message message;

    private ProgressDialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        OcultarTecladoVirtual();

        _personal_id = findViewById(R.id.login_input_personalID);
        _password = findViewById(R.id.login_input_password);
        _loginButton = findViewById(R.id.login_button_login);
        _singUp = findViewById(R.id.login_text_createAccount);

        _password_inputLayout = findViewById(R.id.login_textInputLayout_password);

        message = new Message();

        _loginButton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        dialog = message.waiting("Authenticating",LoginActivity.this);
                        LocalLogin(_personal_id.getText().toString(),_password.getText().toString());
                    }
                });

        _singUp.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View viewIn) {
                        goToCreateAccount();
                    }
                });
    }

    private void LocalLogin(String personal_id, String password){
        login = new LoginController(this);

        String msg = login.isAuthenticated(personal_id, password);
        dialog.dismiss();

        if(msg.equalsIgnoreCase("Connection Successful")){
            logedUser=login.getUser();
            goToMenuUser();
        }else if(msg.equalsIgnoreCase("Enter Personal ID")){
            _personal_id.setError(msg);
        }else if(msg.equalsIgnoreCase("Password Must Have 6 Numbers")){
            _password_inputLayout.setError(msg);
        }else{
            message.alert(msg,this);
        }
    }

    private void goToMenuUser(){
        Intent userMenu = new Intent (getApplicationContext(), UserMenuActivity.class);
        startActivity(userMenu);
    }

    private void goToCreateAccount(){
        Intent createAccount = new Intent (getApplicationContext(), CreateAccountActivity.class);
        startActivity(createAccount);
    }

    private void OcultarTecladoVirtual(){
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
    }

}
