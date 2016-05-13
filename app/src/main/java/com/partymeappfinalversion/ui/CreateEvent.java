package com.partymeappfinalversion.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.partymeappfinalversion.R;

public class CreateEvent extends AppCompatActivity implements View.OnClickListener{

    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        back = (Button) findViewById(R.id.BackToMain);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.BackToMain:
                startActivity(new Intent(this, MapsActivityMain.class));
                break;
            case R.id.CreatEvent:
                startActivity(new Intent(this, CreateEvent.class));
                break;
            case R.id.searchByCategory:
                startActivity(new Intent(this, Register.class));
                break;
        }
    }
}
