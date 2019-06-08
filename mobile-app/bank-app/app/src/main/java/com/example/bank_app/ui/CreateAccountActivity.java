package com.example.bank_app.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bank_app.R;
import com.example.bank_app.logic.Message;
import com.example.bank_app.logic.SignupController;

public class CreateAccountActivity extends AppCompatActivity {

    private EditText _name;
    private EditText _personal_id;
    private EditText _password;

    private TextInputLayout _password_inputLayout;

    private Button _signupButton;

    private TextView _logIn;

    private SignupController signup; //usado para verificar que no existe otro ususario con el  id a registrar.

    private Message message;

    private ProgressDialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        OcultarTecladoVirtual();

        _name = findViewById(R.id.signup_input_fullname);
        _personal_id = findViewById(R.id.signup_input_personalID);
        _password = findViewById(R.id.signup_input_password);

        _password_inputLayout = findViewById(R.id.login_textInputLayout_password);

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
        signup = new SignupController(this); //context used for the path and get the database dir in Database class.

        String msg = signup.IsVerified(name, personal_id, password);

        dialog.dismiss();

        if(msg.equalsIgnoreCase("User & Account Created")){
            successfulAccountCreation();
        }else if(msg.equalsIgnoreCase("User Id Is Already Registered")){
            _personal_id.setError(msg);
        }else if(msg.equalsIgnoreCase("Password must have 6 numbers")){
            _password_inputLayout.setError(msg);
        }else if(msg.equalsIgnoreCase("Enter Personal ID")) {
            _personal_id.setError(msg);
        }else if(msg.equalsIgnoreCase("Enter Name")){
            _name.setError(msg);
        }else if (msg.equalsIgnoreCase("Can´t Create User")){
            _personal_id.setError(msg);
        }else if(msg.equalsIgnoreCase("Personal ID Must Have 8 Numbers At Least")){
            _personal_id.setError(msg);
        }else{
            message.alert(msg,this);
        }
    }

    private void goToLogin(){
        Intent log = new Intent (getApplicationContext(), LoginActivity.class);
        startActivity(log);
    }

    private void successfulAccountCreation(){
        AlertDialog.Builder builder = new AlertDialog.Builder(CreateAccountActivity.this);

        builder.setTitle("Successful Account Creation!");
        builder.setMessage("Now you can use our services.");
        builder.setCancelable(true);

        builder.setPositiveButton("Back to login",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
                goToLogin();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
