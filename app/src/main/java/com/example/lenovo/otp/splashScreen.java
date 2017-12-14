package com.example.lenovo.otp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class splashScreen extends AppCompatActivity {
    LoginDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        db = new LoginDB(getApplicationContext(), "login.db", null, 1);

        final String s = db.chkLogin();
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
        Thread myThred = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    if (s.equals("no") == true) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(getApplicationContext(), PartnerStores.class);
                        startActivity(intent);
                    }
                    finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThred.start();
    }
}
