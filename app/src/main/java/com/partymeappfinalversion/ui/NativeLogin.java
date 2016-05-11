package com.partymeappfinalversion.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.partymeappfinalversion.R;

public class NativeLogin extends AppCompatActivity implements View.OnClickListener{

    Button loginButton;
    EditText uName, pwd;
    TextView regLink;
    UserInLocalDb userInLocalDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_login);

        loginButton = (Button) findViewById(R.id.loginButton);
        uName = (EditText) findViewById(R.id.uName);
        pwd = (EditText) findViewById(R.id.pwd);
        regLink = (TextView) findViewById(R.id.regLink);

        loginButton.setOnClickListener(this);
        regLink.setOnClickListener(this);
        userInLocalDb = new UserInLocalDb(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loginButton:
                //creates a new user
                User newUser = new User(null, null);

                //set user as logged
                userInLocalDb.setLoggedUser(true);
                //store the user name password that has logged in
                userInLocalDb.userDataStore(newUser);

                startActivity(new Intent(this, Logged.class));
                break;
            case R.id.regLink:
                startActivity(new Intent(this, Register.class));
                break;
        }
    }
}
