package com.example.lenovo.otp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class DeliveryDetails1 extends AppCompatActivity {
    TextView DeliveryPriferance,Products;
    Button OrderConform,conformChane,Back,conform1,b9_12,b12_3,b3_6,b6_9;
    RadioGroup radioGroup,redecond;
    RadioButton redioButton,redioButton1,regular,today,express,tomorrow;
    ImageView imageView;
    RelativeLayout relativeLayout;
    int flag=0;
    String selectedItemText;
    EditText name,contact,socityname,flatnoname,landmark,area;
    String result,Srediogroup,Sproduct,Sarea1,Smobile;
    String [] slist={"Express Delivary(45mins @ R45)","Express Delivary(60mins @ R300)","Express Delivary(15mins @ R15)"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_details1);
        radioGroup=findViewById(R.id.rediog);
        Back=findViewById(R.id.btnback);
        redecond=findViewById(R.id.redsecond);
        OrderConform=findViewById(R.id.btn_conform);
        regular=findViewById(R.id.regular);
        today=findViewById(R.id.today);
        express=findViewById(R.id.express);
        tomorrow=findViewById(R.id.tommaro);


        Products=findViewById(R.id.name);

        b9_12=findViewById(R.id.b9_12);
        b12_3=findViewById(R.id.b12_3);
        b3_6=findViewById(R.id.b3_6);
        b6_9=findViewById(R.id.b6_9);

        relativeLayout=findViewById(R.id.relativeLayout);

        Intent intent = getIntent();
        String str = intent.getStringExtra("location");
        Products.setText(str);
        Sproduct=Products.getText().toString();

        name=findViewById(R.id.editTextname);
        contact=findViewById(R.id.editTextcontactnumber);
        socityname=findViewById(R.id.editTextsocityname);
        flatnoname=findViewById(R.id.editTextflatnoname);
        landmark=findViewById(R.id.editTextlandmark);
        area=findViewById(R.id.editTextarea);
        Intent i = getIntent();
        String str1 = i.getStringExtra("Number");
        contact.setText(str1);

        Intent i1 = getIntent();
        String str2 = i1.getStringExtra("Area");
        area.setText(str2);

        Sarea1=area.getText().toString();
        Smobile=contact.getText().toString();

        conformChane=findViewById(R.id.btn_conformchange1);
        conform1=findViewById(R.id.btn_conformchange2);

        DeliveryPriferance=findViewById(R.id.delivarypreference);
        conformChane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderConform.setVisibility(View.VISIBLE);
                DeliveryPriferance.setVisibility(View.VISIBLE);
                radioGroup.setVisibility(View.VISIBLE);
                conformChane.setVisibility(View.GONE);



            }
        });
        conform1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderConform.setVisibility(View.VISIBLE);
                DeliveryPriferance.setVisibility(View.VISIBLE);
                radioGroup.setVisibility(View.VISIBLE);
                conform1.setVisibility(View.GONE);



            }
        });

        b9_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                b9_12.setBackgroundColor(Color.YELLOW);
                b12_3.setBackgroundColor(Color.GRAY);
                b3_6.setBackgroundColor(Color.GRAY);
                b6_9.setBackgroundColor(Color.GRAY);
                flag=1;

            }
        });

        b12_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                b9_12.setBackgroundColor(Color.GRAY);
                b12_3.setBackgroundColor(Color.YELLOW);
                b3_6.setBackgroundColor(Color.GRAY);
                b6_9.setBackgroundColor(Color.GRAY);
                flag=2;

            }
        });

        b3_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                b9_12.setBackgroundColor(Color.GRAY);
                b12_3.setBackgroundColor(Color.GRAY);
                b3_6.setBackgroundColor(Color.YELLOW);
                b6_9.setBackgroundColor(Color.GRAY);
                flag=3;

            }
        });

        b6_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                b9_12.setBackgroundColor(Color.GRAY);
                b12_3.setBackgroundColor(Color.GRAY);
                b3_6.setBackgroundColor(Color.GRAY);
                b6_9.setBackgroundColor(Color.YELLOW);
                flag=4;

            }
        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent=new Intent(getApplicationContext(),listItemAdd.class);
                startActivity(intent);*/
                onBackPressed();
            }
        });


        contact.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {



            }

            @Override
            public void afterTextChanged(Editable s) {

                conform1.setVisibility(View.GONE);
                conformChane.setVisibility(View.VISIBLE);


            }
        });


        final Spinner spinner=findViewById(R.id.list);

        imageView=findViewById(R.id.imageView2);
        name=findViewById(R.id.editTextname);
        redecond.setVisibility(View.GONE);
        relativeLayout=findViewById(R.id.relativeLayout);
        relativeLayout.setVisibility(View.GONE);

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,slist);

        spinner.setAdapter(arrayAdapter);
        result= (String) spinner.getSelectedItem();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){

                    case R.id.regular:
                        redecond.setVisibility(View.VISIBLE);
                        relativeLayout.setVisibility(View.VISIBLE);
                        spinner.setVisibility(View.GONE);
                        break;

                    case R.id.express:
                        spinner.setVisibility(View.VISIBLE);
                        redecond.setVisibility(View.GONE);
                        relativeLayout.setVisibility(View.GONE);
                        break;
                }

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                selectedItemText= (String) adapterView.getItemAtPosition(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        OrderConform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(name.getText().toString().trim().equals("")|| socityname.getText().toString().trim().equals("")||flatnoname.getText().toString().trim().equals("")|| contact.getText().toString().trim().equals("")||area.getText().toString().trim().equals("")) {

                    Toast.makeText(getApplicationContext(), "Plz Enter the fields...!!!", Toast.LENGTH_LONG).show();

                }
                else
                {
                    if (regular.isChecked())
                    {
                        if(today.isChecked()){

                            int r = radioGroup.getCheckedRadioButtonId();
                            regular = findViewById(r);
                            int s=redecond.getCheckedRadioButtonId();
                            today=findViewById(s);

                            if(flag==1) {
                                Toast.makeText(getApplicationContext(), name.getText().toString() + "\n " + socityname.getText().toString() + "\n" + flatnoname.getText().toString() + "\n" + contact.getText().toString() + "\n" + area.getText().toString() + "\n" + regular.getText() + " = " + today.getText() + " = " + b9_12.getText()+"\n"+Sproduct, Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(DeliveryDetails1.this, OrderConformation.class);
                                intent.putExtra("Number", Smobile);
                                intent.putExtra("Area", Sarea1);
                                startActivity(intent);

                            }
                            else if(flag==2) {
                                Toast.makeText(getApplicationContext(), name.getText().toString() + "\n " + socityname.getText().toString() + "\n" + flatnoname.getText().toString() + "\n" + contact.getText().toString() + "\n" + area.getText().toString() + "\n" + regular.getText() + " = " + today.getText() + " = " + b12_3.getText()+"\n"+Sproduct, Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(DeliveryDetails1.this, OrderConformation.class);
                                intent.putExtra("Number", Smobile);
                                intent.putExtra("Area", Sarea1);
                                startActivity(intent);
                            }
                            else if(flag==3) {
                                Toast.makeText(getApplicationContext(), name.getText().toString() + "\n " + socityname.getText().toString() + "\n" + flatnoname.getText().toString() + "\n" + contact.getText().toString() + "\n" + area.getText().toString() + "\n" + regular.getText() + " = " + today.getText() + " = " + b3_6.getText()+"\n"+Sproduct, Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(DeliveryDetails1.this, OrderConformation.class);
                                intent.putExtra("Number", Smobile);
                                intent.putExtra("Area", Sarea1);
                                startActivity(intent);
                            }
                            else if(flag==4) {
                                Toast.makeText(getApplicationContext(), name.getText().toString() + "\n " + socityname.getText().toString() + "\n" + flatnoname.getText().toString() + "\n" + contact.getText().toString() + "\n" + area.getText().toString() + "\n" + regular.getText() + " = " + today.getText() + " = " + b6_9.getText()+"\n"+Sproduct, Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(DeliveryDetails1.this, OrderConformation.class);
                                intent.putExtra("Number", Smobile);
                                intent.putExtra("Area", Sarea1);
                                startActivity(intent);
                            }
                            else {

                                Toast.makeText(getApplicationContext(), "Plz Select Time Slot...!!!", Toast.LENGTH_LONG).show();
                            }

                        }
                        else if(tomorrow.isChecked()){

                            int r = radioGroup.getCheckedRadioButtonId();
                            regular = findViewById(r);
                            int s=redecond.getCheckedRadioButtonId();
                            tomorrow=findViewById(s);

                            if(flag==1) {
                                Toast.makeText(getApplicationContext(), name.getText().toString() + "\n " + socityname.getText().toString() + "\n" + flatnoname.getText().toString() + "\n" + contact.getText().toString() + "\n" + area.getText().toString() + "\n" + regular.getText() + " = " + tomorrow.getText() + " = " + b9_12.getText()+"\n"+Sproduct, Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(DeliveryDetails1.this, OrderConformation.class);
                                intent.putExtra("Number", Smobile);
                                intent.putExtra("Area", Sarea1);
                                startActivity(intent);

                            }
                            else if(flag==2) {
                                Toast.makeText(getApplicationContext(), name.getText().toString() + "\n " + socityname.getText().toString() + "\n" + flatnoname.getText().toString() + "\n" + contact.getText().toString() + "\n" + area.getText().toString() + "\n" + regular.getText() + " = " + tomorrow.getText() + " = " + b12_3.getText()+"\n"+Sproduct, Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(DeliveryDetails1.this, OrderConformation.class);
                                startActivity(intent);
                            }
                            else if(flag==3) {
                                Toast.makeText(getApplicationContext(), name.getText().toString() + "\n " + socityname.getText().toString() + "\n" + flatnoname.getText().toString() + "\n" + contact.getText().toString() + "\n" + area.getText().toString() + "\n" + regular.getText() + " = " + tomorrow.getText() + " = " + b3_6.getText()+"\n"+Sproduct, Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(DeliveryDetails1.this, OrderConformation.class);
                                intent.putExtra("Number", Smobile);
                                intent.putExtra("Area", Sarea1);
                                startActivity(intent);
                            }
                            else if(flag==4) {
                                Toast.makeText(getApplicationContext(), name.getText().toString() + "\n " + socityname.getText().toString() + "\n" + flatnoname.getText().toString() + "\n" + contact.getText().toString() + "\n" + area.getText().toString() + "\n" + regular.getText() + " = " + tomorrow.getText() + " = " + b6_9.getText()+"\n"+Sproduct, Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(DeliveryDetails1.this, OrderConformation.class);
                                intent.putExtra("Number", Smobile);
                                intent.putExtra("Area", Sarea1);
                                startActivity(intent);
                            }
                            else {

                                Toast.makeText(getApplicationContext(), "Plz Select Time Slot...!!!", Toast.LENGTH_LONG).show();
                            }
                        }
                        else {

                            Toast.makeText(getApplicationContext(), "Plz Select Delivary Preference...!!!", Toast.LENGTH_LONG).show();
                        }
                    }

                    else if(express.isChecked()) {

                        Toast.makeText(getApplicationContext(), name.getText().toString() + "\n " + socityname.getText().toString() + "\n" + flatnoname.getText().toString() + "\n" + contact.getText().toString() + "\n" + area.getText().toString() + "\n" + express.getText()+" = "+selectedItemText+"\n"+Sproduct, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(DeliveryDetails1.this, OrderConformation.class);
                        intent.putExtra("Number", Smobile);
                        intent.putExtra("Area", Sarea1);
                        startActivity(intent);

                    }
                    else {

                        Toast.makeText(getApplicationContext(), "Plz Select Delivary Preference...!!!", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

    }


   /* @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(DeliveryDetails1.this, listItemAdd.class));
        finish();

    }*/
 /*  public void rbclick(View view){

        int redioid=redecond.getCheckedRadioButtonId();
        redioButton=findViewById(redioid);
        Toast.makeText(getApplicationContext(),redioButton.getText(),Toast.LENGTH_LONG).show();

    }*/

   /* public void rbclick1(View view){

        int redioid1=radioGroup.getCheckedRadioButtonId();
        redioButton1=findViewById(redioid1);
        Srediogroup=redioButton1.getText().toString();
        *//*Toast.makeText(getApplicationContext(),redioButton1.getText(),Toast.LENGTH_LONG).show();*//*

    }*/
}
