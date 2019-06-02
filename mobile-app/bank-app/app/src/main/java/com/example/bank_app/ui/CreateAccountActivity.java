package com.example.bank_app.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bank_app.R;
import com.example.bank_app.logic.Message;
import com.example.bank_app.logic.Signup;

public class CreateAccountActivity extends AppCompatActivity {

    EditText _name;
    EditText _personal_id;
    EditText _password;

    Button _signupButton;

    TextView _logIn;

    Signup signup; //usado para verificar que no existe otro ususario con el  id a registrar.

    Message message;

    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        OcultarTecladoVirtual();

        _name = findViewById(R.id.signup_input_fullname);
        _personal_id = findViewById(R.id.signup_input_personalID);
        _password = findViewById(R.id.signup_input_password);

        _signupButton = findViewById(R.id.signup_button_createAccount);
        _logIn = findViewById(R.id.signup_text_login);

        message = new Message();

        _signupButton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        dialog = message.waiting("Creating",CreateAccountActivity.this);
                        CreateAccount(_name.getText().toString(),_personal_id.getText().toString(),_password.getText().toString());
                    }
                });
        _logIn.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View viewIn) {
                        goToLogin();
                    }
                });
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
    }

    private void OcultarTecladoVirtual(){
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
    }

    private void CreateAccount(String name, String personal_id, String password){
        signup = new Signup(this); //context used for the path and get the database dir in Database class.

        String msg = signup.IsVerified(name, personal_id, password);

        dialog.dismiss();

        if(msg.equalsIgnoreCase("User & Account Created")){
            //menuUser();
            //show a panel that says "successful shit"
            _personal_id.setError(msg);
        }else if(msg.equalsIgnoreCase("User Id Is Already Registered")){
            _personal_id.setError(msg);
        }else if(msg.equalsIgnoreCase("Password must have 6 numbers")){
            _password.setError(msg);
        }else if(msg.equalsIgnoreCase("Enter Personal ID")) {
            _personal_id.setError(msg);
        }else if(msg.equalsIgnoreCase("Enter Name")){
            _name.setError(msg);
        }else if (msg.equalsIgnoreCase("Can´t Create User")){
            _personal_id.setError(msg);
        }else{
            message.alert(msg,this);
        }
    }

    private void goToLogin(){
        Intent log = new Intent (getApplicationContext(), LoginActivity.class);
        startActivity(log);
    }

}
