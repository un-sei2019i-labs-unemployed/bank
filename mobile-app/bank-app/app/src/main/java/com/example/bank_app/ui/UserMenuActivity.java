package com.example.bank_app.ui;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import com.example.bank_app.dataAccess.models.User;

import com.example.bank_app.R;
import com.example.bank_app.logic.ViewUser;

import static com.example.bank_app.ui.LoginActivity.logedUser;

public class UserMenuActivity extends AppCompatActivity {
    ViewUser accountData;
    private User currentUser;

    private Button buttonLogout;

    //Views para Profile Panel
    private TextView mTextMessage, textID, textName, textAccount, textBalance;
    private TextView text_value_ID, text_value_Name, text_value_Account, text_value_Balance;

    //Views para TRANSACTION Panel
    private LinearLayout transactionLayout;
    private TextView textTransactionTitle, textTargetUserInfo, textAmmount;
    private TextInputLayout tilAccountID, tilAmmount;
    private TextInputEditText tietAccountID, tiedAmmount;
    private Button transactionButtonSend;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);

                    setProfilePanelVisibility(View.VISIBLE);
                    setTransactionPanelVisibility(View.INVISIBLE);

                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);

                    setProfilePanelVisibility(View.INVISIBLE);
                    setTransactionPanelVisibility(View.VISIBLE);

                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);

                    setProfilePanelVisibility(View.INVISIBLE);
                    setTransactionPanelVisibility(View.INVISIBLE);

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        currentUser = logedUser;

        accountData = new ViewUser(this);
        accountData.viewAccount(currentUser.getPersonal_id());

        setContentView(R.layout.activity_user_menu);

        BottomNavigationView navView = findViewById(R.id.nav_view);

        initViews();

        setProfilePanelVisibility(View.VISIBLE);
        setTransactionPanelVisibility(View.INVISIBLE);

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        setValues(currentUser,accountData);

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserMenuActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initViews(){
        mTextMessage = findViewById(R.id.title_panel);
        textID = findViewById(R.id.textID);
        textName = findViewById(R.id.textName);
        textAccount = findViewById(R.id.textAcount);
        textBalance = findViewById(R.id.textBalance);

        text_value_ID = findViewById(R.id.text_value_personalId);
        text_value_Name = findViewById(R.id.text_value_name);
        text_value_Account = findViewById(R.id.text_value_accountId);
        text_value_Balance = findViewById(R.id.text_value_balance);

        buttonLogout = findViewById(R.id.menu_button_logout);
//=======================TRANSACTION
        transactionLayout = findViewById(R.id.transaction_linearLayout);

            textTransactionTitle = findViewById(R.id.transaction_text_title);
            textTargetUserInfo = findViewById(R.id.transaction_text_targetUserInfo);

                tilAccountID = findViewById(R.id.transaction_textInputLayout_accountID);
                tietAccountID = findViewById(R.id.transaction_input_accountID);

            textAmmount = findViewById(R.id.transaction_text_ammount);

                tilAmmount = findViewById(R.id.transaction_textInputLayout_ammount);
                tiedAmmount = findViewById(R.id.transaction_input_ammount);

            transactionButtonSend = findViewById(R.id.transaction_button_send);
    }
    private void setValues(User _currentUser, ViewUser _accountData){
        text_value_ID.setText( Integer.toString(_currentUser.getPersonal_id()) );
        text_value_Name.setText(_currentUser.getName());
        text_value_Account.setText(Integer.toString(_accountData.getAccount().getNo_accoun()));
        text_value_Balance.setText("$ "+Integer.toString(_accountData.getAccount().getBalance()));
    }

    private void setProfilePanelVisibility(int _visibility){
        textID.setVisibility(_visibility);
        textName.setVisibility(_visibility);
        textAccount.setVisibility(_visibility);
        textBalance.setVisibility(_visibility);

        text_value_ID.setVisibility(_visibility);
        text_value_Name.setVisibility(_visibility);
        text_value_Account.setVisibility(_visibility);
        text_value_Balance.setVisibility(_visibility);
    }
    private void setTransactionPanelVisibility(int _visibility){

        transactionLayout.setVisibility(_visibility);
        textTransactionTitle.setVisibility(_visibility);
        textTargetUserInfo.setVisibility(_visibility);
        tilAccountID.setVisibility(_visibility);

        tietAccountID.setVisibility(_visibility);
        textAmmount.setVisibility(_visibility);
        tilAmmount.setVisibility(_visibility);
        tiedAmmount.setVisibility(_visibility);

        transactionButtonSend.setVisibility(_visibility);
    }
}
