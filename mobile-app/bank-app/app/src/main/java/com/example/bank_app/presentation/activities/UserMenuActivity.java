package com.example.bank_app.presentation.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

import com.example.bank_app.businessLogic.controllers.AccountAutenticatorController;
import com.example.bank_app.businessLogic.controllers.TransactionController;
import com.example.bank_app.dataAccess.models.User;

import com.example.bank_app.R;
import com.example.bank_app.businessLogic.*;

import static com.example.bank_app.presentation.activities.LoginActivity.logedUser;

public class UserMenuActivity extends AppCompatActivity {
    AccountAutenticatorController accountData;
    TransactionController transaction;
    private User currentUser;

    private Button buttonLogout, buttonRecharge;

    //Views para Profile Panel
    private TextView mTextMessage;
    private TextView text_value_ID, text_value_Name, text_value_Account, text_value_Balance;

    //Views para TRANSACTION Panel
    private LinearLayout transactionLayout;
    private GridLayout profileLayout;

    private TextInputLayout tilAccountID, tilAmount;
    private TextInputEditText tietAccountID, tiedAmount;
    private Button transactionButtonSend;
    private Message message;

    private ProgressDialog dialog;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);

                    profileLayout.setVisibility(View.VISIBLE);
                    transactionLayout.setVisibility(View.INVISIBLE);

                    text_value_Balance.setText("$ "+Integer.toString(accountData.getAccount().getBalance()));

                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);

                    profileLayout.setVisibility(View.INVISIBLE);
                    transactionLayout.setVisibility(View.VISIBLE);

                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);

                    profileLayout.setVisibility(View.INVISIBLE);
                    transactionLayout.setVisibility(View.INVISIBLE);

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        currentUser = logedUser;

        accountData = new AccountAutenticatorController(this);
        accountData.viewAccount(currentUser.getPersonal_id());

        setContentView(R.layout.activity_user_menu);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        message = new Message();

        initViews();

        profileLayout.setVisibility(View.VISIBLE);
        transactionLayout.setVisibility(View.INVISIBLE);

        //setProfilePanelVisibility(View.VISIBLE);
        //setTransactionPanelVisibility(View.INVISIBLE);

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        setValues(currentUser,accountData);

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserMenuActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        transactionButtonSend.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        if(transactionButtonSend.getVisibility()==View.VISIBLE) {
                            dialog = message.waiting("Authenticating", UserMenuActivity.this);
                            validateTransaction(currentUser.getPersonal_id(),tietAccountID.getText().toString(), tiedAmount.getText().toString(),accountData.getAccount().getBalance());
                        }
                    }
                });
        buttonRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accountData.refill(currentUser.getPersonal_id());
            }
        });
    }

    private void validateTransaction(int origin,String accountID, String amount, int currentBalance){
        transaction =new TransactionController(this);
        String msg = transaction.isVerified(origin, accountID,amount,currentBalance);
        dialog.dismiss();

        if(msg.equalsIgnoreCase("Succesful transaction!")){
            message.alert(msg,this);
        }else if(msg.equalsIgnoreCase("Enter a valid Account ID")){
            tilAccountID.setError(msg);
        }else if(msg.equalsIgnoreCase("There isn't a valid amount")){
            tilAmount.setError(msg);
        }else{
            message.alert(msg,this);
        }
    }

    private void initViews(){
        mTextMessage = findViewById(R.id.title_panel);

        text_value_ID = findViewById(R.id.text_value_personalId);
        text_value_Name = findViewById(R.id.text_value_name);
        text_value_Account = findViewById(R.id.text_value_accountId);
        text_value_Balance = findViewById(R.id.text_value_balance);

        buttonLogout = findViewById(R.id.menu_button_logout);
        buttonRecharge=findViewById(R.id.recharge);
//=======================TRANSACTION
        transactionLayout = findViewById(R.id.transaction_linearLayout);
        profileLayout = findViewById(R.id.profile_gridLayout);


                tilAccountID = findViewById(R.id.transaction_textInputLayout_accountID);
                tietAccountID = findViewById(R.id.transaction_input_accountID);

                tilAmount = findViewById(R.id.transaction_textInputLayout_amount);
                tiedAmount = findViewById(R.id.transaction_input_amount);

            transactionButtonSend = findViewById(R.id.transaction_button_send);
    }
    private void setValues(User _currentUser, AccountAutenticatorController _accountData){
        text_value_ID.setText( Integer.toString(_currentUser.getPersonal_id()) );
        text_value_Name.setText(_currentUser.getName());
        text_value_Account.setText(Integer.toString(_accountData.getAccount().getNo_accoun()));
        text_value_Balance.setText("$ "+Integer.toString(_accountData.getAccount().getBalance()));
    }

}