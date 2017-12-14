package com.example.lenovo.otp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class recentOrder extends AppCompatActivity {
    TextView Partenetstor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_order);
        Partenetstor=findViewById(R.id.tv_partenerstores);
        Partenetstor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), PartnerStores.class);
                startActivity(intent);
            }
        });
    }
}
