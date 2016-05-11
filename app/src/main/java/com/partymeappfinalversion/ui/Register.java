package com.partymeappfinalversion.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.partymeappfinalversion.R;

public class Register extends AppCompatActivity implements View.OnClickListener {

    EditText nameVar, emalVar, pwdVar, pwdVar2;
    Button registerButton;
    TextView backToLogin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameVar = (EditText) findViewById(R.id.nameVar);
        emalVar = (EditText) findViewById(R.id.emailVar);
        pwdVar = (EditText) findViewById(R.id.pwdVar);
        pwdVar2 = (EditText) findViewById(R.id.pwdVar2);

        registerButton = (Button) findViewById(R.id.registerButton);
        backToLogin = (TextView) findViewById(R.id.backToLogin);

        backToLogin.setOnClickListener(this);
        registerButton.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registerButton:
                //startActivity(new Intent(this, Login.class));
                String name = nameVar.getText().toString();
                String email = emalVar.getText().toString();
                String password = pwdVar.getText().toString();
                String rePassword = pwdVar2.getText().toString();

                User registeredUserData = new User(name, email, password, rePassword);
                break;

            case R.id.backToLogin:
                startActivity(new Intent(this, NativeLogin.class));
        }

    }
}
