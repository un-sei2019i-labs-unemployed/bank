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
    private TextView mTextMessage, textID, textName, textAcount, textBalance;
    private Button buttonLogout;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    textID.setVisibility(View.VISIBLE);
                    textName.setVisibility(View.VISIBLE);
                    textAcount.setVisibility(View.VISIBLE);
                    textBalance.setVisibility(View.VISIBLE);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    textID.setVisibility(View.INVISIBLE);
                    textName.setVisibility(View.INVISIBLE);
                    textAcount.setVisibility(View.INVISIBLE);
                    textBalance.setVisibility(View.INVISIBLE);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    textID.setVisibility(View.INVISIBLE);
                    textName.setVisibility(View.INVISIBLE);
                    textAcount.setVisibility(View.INVISIBLE);
                    textBalance.setVisibility(View.INVISIBLE);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentUser=logedUser;
        accountData=new ViewUser(this);
        accountData.viewAccount(currentUser.getPersonal_id());
        setContentView(R.layout.activity_user_menu);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        textID = findViewById(R.id.textID);
        textName = findViewById(R.id.textName);
        textAcount = findViewById(R.id.textAcount);
        textBalance = findViewById(R.id.textBalance);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        buttonLogout = (Button) findViewById(R.id.LogoutButton);

        textID.setText("ID "+currentUser.getPersonal_id());
        textName.setText("propietario "+currentUser.getName());
        textAcount.setText("cuenta identificada con "+accountData.getAccount().getNo_accoun());
        textBalance.setText("balance de $"+accountData.getAccount().getBalance());

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserMenuActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

}
