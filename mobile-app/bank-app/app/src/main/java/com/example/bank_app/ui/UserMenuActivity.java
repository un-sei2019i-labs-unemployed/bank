package com.example.bank_app.ui;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
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
    private TextView mTextMessage, textID, textName, textAccount, textBalance;
    private TextView text_value_ID, text_value_Name, text_value_Account, text_value_Balance;
    private Button buttonLogout;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);

                    setProfilePanelVisibility(View.VISIBLE);

                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);

                    setProfilePanelVisibility(View.INVISIBLE);

                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);

                    setProfilePanelVisibility(View.INVISIBLE);

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
        mTextMessage = findViewById(R.id.message);
        textID = findViewById(R.id.textID);
        textName = findViewById(R.id.textName);
        textAccount = findViewById(R.id.textAcount);
        textBalance = findViewById(R.id.textBalance);

        text_value_ID = findViewById(R.id.text_value_personalId);
        text_value_Name = findViewById(R.id.text_value_name);
        text_value_Account = findViewById(R.id.text_value_accountId);
        text_value_Balance = findViewById(R.id.text_value_balance);

        buttonLogout = findViewById(R.id.LogoutButton);
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
}
