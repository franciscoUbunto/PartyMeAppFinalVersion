package com.partymeappfinalversion.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.partymeappfinalversion.R;

public class Logged extends AppCompatActivity implements View.OnClickListener{

    EditText nameLogged, emailReg;
    Button logout;
    //initialize the class that stores users data
    UserInLocalDb userInLocalDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged);

        nameLogged = (EditText) findViewById(R.id.nameLogged);
        emailReg = (EditText) findViewById(R.id.emailLogged);

        logout = (Button) findViewById(R.id.logoutButton);
        logout.setOnClickListener(this);

        //create instace of user data in userInlocalDb
        userInLocalDb = new UserInLocalDb(this);

    }

    @Override
    public void onStart(){
        super.onStart();
        if (checkUserStatus() == true){
            displayUserDetails();
        }
    }
    //display informations about user logged
    public void displayUserDetails(){
        User user = userInLocalDb.getLoggedUser();
        nameLogged.setText(user.name);
        emailReg.setText(user.email);
    }

    //verify if user has logged in / out
    public boolean checkUserStatus(){
        return userInLocalDb.userLogged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.logoutButton:
                userInLocalDb.clearDataFromUser();
                userInLocalDb.setLoggedUser(false);

                startActivity(new Intent(this, NativeLogin.class));
                break;
        }
    }
}
