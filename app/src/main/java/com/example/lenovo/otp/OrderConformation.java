package com.example.lenovo.otp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OrderConformation extends AppCompatActivity {
    Button Done;
    TextView placeanatherorder,contactnumber,areafromdelivery;
    String contact,area1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_conformation);
        Done=findViewById(R.id.btn_done);
        placeanatherorder=findViewById(R.id.placeanatherorder);
        contactnumber=findViewById(R.id.tv_contactnumberfromdeliv);
        Intent intent = getIntent();
        String str = intent.getStringExtra("Number");
        contactnumber.setText(str);
        contact=contactnumber.getText().toString();

        areafromdelivery=findViewById(R.id.tv_areafromdelivery);
        Intent i = getIntent();
        String area = i.getStringExtra("Area");
        areafromdelivery.setText(area);

        area1=areafromdelivery.getText().toString();


        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), PartnerStores.class);
                intent.putExtra("Number", contact);
                intent.putExtra("Area", area1);
                startActivity(intent);
            }
        });
        placeanatherorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), listItemAdd.class);
                intent.putExtra("Number", contact);
                intent.putExtra("Area", area1);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(OrderConformation.this, DeliveryDetails1.class));
        finish();

    }
}
