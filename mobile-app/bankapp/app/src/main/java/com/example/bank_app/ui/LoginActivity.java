package com.example.bank_app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.bank_app.R;
import com.example.bank_app.data.ConexionSQLiteHelper;
import com.example.bank_app.data.User;

public class LoginActivity extends AppCompatActivity {

    ConexionSQLiteHelper con;

    EditText personal_id;
    EditText password;

    TextInputLayout textInputLayoutPersonalID;
    TextInputLayout textInputLayoutPassword;

    //Declaration Button
    Button buttonLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        con = new  ConexionSQLiteHelper(this);
        initViews();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Check user input is correct or not
                if (validate()) {

                    //Get values from EditText fields
                    String Username = personal_id.getText().toString();
                    int Password = Integer.parseInt(password.getText().toString());

                    //Authenticate user
                    User currentUser = null;
                    try {
                        currentUser = con.Loggin(new User(0,Username,Password));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    //Check Authentication is successful or not
                    if (currentUser != null) {
                        Snackbar.make(buttonLogin, "Successfully Logged in!", Snackbar.LENGTH_LONG).show();
                        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                        startActivity(intent);

                        //User Logged in Successfully Launch You home screen activity
                       /* Intent intent=new Intent(LoginActivity.this,HomeScreenActivity.class);
                        startActivity(intent);
                        finish();*/
                    } else {

                        //User Logged in Failed
                        Snackbar.make(buttonLogin, "Failed to log in , please try again", Snackbar.LENGTH_LONG).show();

                    }
                }
            }
        });

    }


    private void initViews() {
        personal_id = (EditText) findViewById(R.id.editTextUsername);
        password = (EditText) findViewById(R.id.editTextPassword);
        textInputLayoutPersonalID = (TextInputLayout) findViewById(R.id.textInputLayoutUsername);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);

    }

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html) {
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }

    public boolean validate() {
        boolean valid = false;

        //Get values from EditText fields
        String Username = personal_id.getText().toString();
        String Password = password.getText().toString();

        //Handling validation for Username field
        if (Username.isEmpty()) {
            valid = false;
            textInputLayoutPersonalID.setError("Please enter valid personal ID!");
            return valid;
        } else {
            if (Username.length() > 5) {
                valid = true;
                textInputLayoutPersonalID.setError(null);
            } else {
                valid = false;
                textInputLayoutPersonalID.setError("Personal ID is too short!");
                return valid;
            }
        }

        //Handling validation for Password field
        if (Password.isEmpty()) {
            valid = false;
            textInputLayoutPassword.setError("Please enter valid password!");
            return valid;
        } else {
            if (Password.matches("\\d+")) {
                valid = true;
                textInputLayoutPassword.setError(null);
            } else {
                valid = false;
                textInputLayoutPassword.setError("Password must be numeric!");
                return valid;
            }

            if (Password.length() == 6) {
                valid = true;
                textInputLayoutPassword.setError(null);
            } else {
                valid = false;
                textInputLayoutPassword.setError("Password length must be 6!");
                return valid;
            }

        }

        return valid;
    }
}
