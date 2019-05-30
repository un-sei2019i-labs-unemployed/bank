package com.example.loginapp;

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
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    //Declaration EditTexts
    EditText editTextUser;
    EditText editTextPassword;

    //Declaration TextInputLayout
    TextInputLayout textInputLayoutUser;
    TextInputLayout textInputLayoutPassword;

    //Declaration Button
    Button buttonLogin;

    //Declaration SqliteHelper
    SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sqliteHelper = new SqliteHelper(this);
        initCreateAccountTextView();
        initViews();

        //set click event of login button
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Check user input is correct or not
                if (validate()) {

                    //Get values from EditText fields
                    String userName = editTextUser.getText().toString();
                    int Password = Integer.parseInt(editTextPassword.getText().toString());

                    //Authenticate user
                    User currentUser = sqliteHelper.Authenticate(new User(0, userName, /*Email,*/ Password));

                    //Check Authentication is successful or not
                    if (currentUser != null) {
                        Snackbar.make(buttonLogin, "Successfully Logged in!", Snackbar.LENGTH_LONG).show();

                        //User Logged in Successfully Launch You home screen activity
                       Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        //finish();
                    } else {

                        //User Logged in Failed
                        Snackbar.make(buttonLogin, "Failed to log in , please try again", Snackbar.LENGTH_LONG).show();

                    }
                }
            }
        });


    }

    //this method used to set Create account TextView text and click event( maltipal colors
    // for TextView yet not supported in Xml so i have done it programmatically)
    private void initCreateAccountTextView() {
        TextView textViewCreateAccount = (TextView) findViewById(R.id.textViewCreateAccount);
        textViewCreateAccount.setText(fromHtml("<font color='#ffffff'>I don't have account yet. </font><font color='#0c0099'>create one</font>"));
        textViewCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    //this method is used to connect XML views to its Objects
    private void initViews() {
        editTextUser = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textInputLayoutUser = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);

    }

    //This method is for handling fromHtml method deprecation
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

    //This method is used to validate input given by user
    public boolean validate() {
        boolean valid = false,validE = false;

        //Get values from EditText fields
        String userName = editTextUser.getText().toString();
        String Password = editTextPassword.getText().toString();

        //Handling validation for Email field
        if (userName.isEmpty()) {
            validE = false;
            textInputLayoutUser.setError("Please enter valid user name!");
        } else {
            validE = true;
            textInputLayoutUser.setError(null);
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

        return valid&&validE;
    }

    public static boolean isNumber(String key){
        char[] keyChars=key.toCharArray();
        for(char l:keyChars){
            if(!(l=='0'||l=='1'||l=='2'||l=='3'||l=='4'||l=='5'||l=='6'||l=='7'||l=='8'||l=='9')){
                return false;
            }
        }
        return true;
    }


}