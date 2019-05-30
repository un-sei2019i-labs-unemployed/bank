package com.example.loginapp;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.loginapp.LoginActivity.isNumber;


public class RegisterActivity extends AppCompatActivity {

    //Declaration EditTexts
    EditText editTextUserName;
    EditText editTextID;
    EditText editTextPassword;

    //Declaration TextInputLayout
    TextInputLayout textInputLayoutUserName;
    TextInputLayout textInputLayoutID;
    TextInputLayout textInputLayoutPassword;

    //Declaration Button
    Button buttonRegister;

    //Declaration SqliteHelper
    SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        sqliteHelper = new SqliteHelper(this);
        initTextViewLogin();
        initViews();
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    String UserName = editTextUserName.getText().toString();
                    int id = Integer.parseInt(editTextID.getText().toString());
                    int Password = Integer.parseInt(editTextPassword.getText().toString());

                    //Check in the database is there any user associated with  this email
                    if (!sqliteHelper.isUserExists(UserName)) {

                        //Email does not exist now add new user to database
                        sqliteHelper.addUser(new User(0, UserName, /*Email,*/ Password));
                        Snackbar.make(buttonRegister, "User created successfully! Please Login ", Snackbar.LENGTH_LONG).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, Snackbar.LENGTH_LONG);
                    }else {

                        //Email exists with email input provided so show error user already exist
                        Snackbar.make(buttonRegister, "ya existe este usuario registrado ", Snackbar.LENGTH_LONG).show();
                    }


                }
            }
        });
    }

    //this method used to set Login TextView click event
    private void initTextViewLogin() {
        TextView textViewLogin = (TextView) findViewById(R.id.textViewLogin);
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //this method is used to connect XML views to its Objects
    private void initViews() {
        editTextID = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        textInputLayoutID = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutUserName = (TextInputLayout) findViewById(R.id.textInputLayoutUserName);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);

    }

    //This method is used to validate input given by user
    public boolean validate() {
        boolean validU = false, valid=false, validP=false;

        //Get values from EditText fields
        String UserName = editTextUserName.getText().toString();
        String ID = editTextID.getText().toString();
        String Password = editTextPassword.getText().toString();

        //Handling validation for UserName field
        if (UserName.isEmpty()) {
            validU = false;
            textInputLayoutUserName.setError("Please enter valid username!");
        } else {
            if (UserName.length() >= 3) {
                validU = true;
                textInputLayoutUserName.setError(null);
            } else {
                validU = false;
                textInputLayoutUserName.setError("Username is to short!");
            }
        }

        //Handling validation for ID field
        if (ID.isEmpty()) {
            validP = false;
            textInputLayoutID.setError("introduzca una identificación válida");
        } else {
            if(ID.length()>=8&&isNumber(ID)){
                validP = true;
                textInputLayoutID.setError(null);
            }else if(!isNumber(ID)){
                validP = false;
                textInputLayoutPassword.setError("La identificación debe ser numérica");
            }else{
                validP = false;
                textInputLayoutPassword.setError("La identificación debe tener más de 8 caracteres");
            }
        }

        //Handling validation for Password field
        if (Password.isEmpty()) {
            valid = false;
            textInputLayoutPassword.setError("¡ingrese una contraseña válida!");
        } else {
            if (Password.length() == 6 && isNumber(Password)) {
                valid = true;
                textInputLayoutPassword.setError(null);
            } else if(!isNumber(Password)){
                valid = false;
                textInputLayoutPassword.setError("La contraseña debe ser numérica");
            }else{
                valid = false;
                textInputLayoutPassword.setError("La contraseña debe tener exactamente 6 caracteres");
            }
        }


        return validU&&valid&&validP;
    }
}