package com.example.lenovo.otp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AreaNotfound extends AppCompatActivity {
    Button continue1,exit1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_notfound);
        continue1=findViewById(R.id.btn_continue);

        continue1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),PartnerStores.class);
                startActivity(intent);
            }
        });


    }
}
