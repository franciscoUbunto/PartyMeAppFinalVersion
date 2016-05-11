package com.partymeappfinalversion.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.partymeappfinalversion.R;

public class ActivitySplash extends Activity {

    private TextView loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        loading = (TextView) findViewById(R.id.activity_splash_loading);

        goToActivitySignin();
    }

    private void goToActivitySignin() {

        new AsyncTask<Object, Object, Object>() {
            @Override
            protected void onPreExecute() {
                loading.setText("Iniciando a conexao");
            }

            @Override
            protected Object doInBackground(Object[] params) {

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                loading.setText("Mais 2 seundos");

                new Handler(Looper.myLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(ActivitySplash.this, ActivitySignin.class));
                    }
                }, 500);

            }
        }.execute();

    }
}
