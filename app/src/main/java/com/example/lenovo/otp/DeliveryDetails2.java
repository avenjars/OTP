package com.example.lenovo.otp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DeliveryDetails2 extends AppCompatActivity {
    Button Conformchange;
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_details2);
        Conformchange=findViewById(R.id.conformChanges);
        name=findViewById(R.id.editTextname);

        Conformchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), OrderConformation.class);
                startActivity(intent);


            }
        });

        Intent i=getIntent();
        name.setText(i.getStringExtra("text"));

    }
}
