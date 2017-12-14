package com.example.lenovo.otp;

import android.app.DownloadManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.Response;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class listItemAdd extends AppCompatActivity {
    private AtomPayListAdapter adapter;
    private ListView listView;
   private List<AtomPayment> mProductList;
   ArrayList<AtomPayment> products = new ArrayList<AtomPayment>();
   ConstraintLayout constraintLayout;

    TextView textView,mobilenumberonlisttext,areafromhome;
    final int REQUEST_CAMERA=1;


  //  ImageView imageupload;

    EditText mainedittext;
    Button placeorder,Adaptrer,menubar;
    LinearLayout linearLayout;
    Bitmap bitmap;
    String uploadUrl="http://www.avenjars.com/upload.php";
    String mobonlist,areahome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item_add);setupListViewAdapter();
        mainedittext= (EditText) findViewById(R.id.mainedittext);
        textView=findViewById(R.id.clickphoto);
        constraintLayout=findViewById(R.id.click);
       // imageupload=findViewById(R.id.imageupload);
        listView =findViewById(R.id.EnterPays_atomPaysList);
        menubar=findViewById(R.id.menu_dotslist);
        /*placeorder=findViewById(R.id.placeorder);*/
        areafromhome=findViewById(R.id.tv_areafromhome);

        Intent i = getIntent();
        String area = i.getStringExtra("Area");
        areafromhome.setText(area);


        mobilenumberonlisttext=findViewById(R.id.tv_contactnumeronlist);

        Intent intent = getIntent();
        String str = intent.getStringExtra("Number");
        mobilenumberonlisttext.setText(str);



        menubar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(listItemAdd.this, menubar);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.main, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        int id=item.getItemId();
                        if (id==R.id.one){
                            Intent intent=new Intent(getApplicationContext(), AboutUs.class);
                            startActivity(intent);
                            return true;
                        }
                        if (id==R.id.two){
                            Intent intent=new Intent(getApplicationContext(), TermsAndCondition.class);
                            startActivity(intent);
                            return true;
                        }
                        if (id==R.id.three){
                            Intent intent=new Intent(getApplicationContext(), MyOrder1.class);
                            startActivity(intent);
                            return true;
                        }
                        if (id==R.id.four){
                            Intent intent=new Intent(getApplicationContext(), PartnerStores.class);
                            startActivity(intent);
                            return true;
                        }

                        Toast.makeText(
                                listItemAdd.this,
                                "You Clicked : " + item.getTitle(),
                                Toast.LENGTH_SHORT
                        ).show();
                        return true;
                    }
                });

                popup.show(); //showing popup menu
            }
        }); //closing the setOnClickListener method








        linearLayout=findViewById(R.id.layout);
        setupAddPaymentButton();

       /* placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StringRequest stringRequest=new StringRequest(Request.Method.POST, uploadUrl, new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();

                    }
                }, new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getApplicationContext(),"error"+error.toString(),Toast.LENGTH_LONG).show();

                    }
                }){

                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params=new HashMap<>();
                        String imageData=imageToString(bitmap);
                        params.put("image",imageData);
                        return params;
                    }
                };
                RequestQueue requestQueue= Volley.newRequestQueue(listItemAdd.this);
                requestQueue.add(stringRequest);
                Intent intent=new Intent(getApplicationContext(),DeliveryDetails1.class);
                startActivity(intent);

            }
        });*/

        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listView.setVisibility(View.GONE);
                linearLayout.setVisibility(View.VISIBLE);
                ActivityCompat.requestPermissions(listItemAdd.this,new String[]{android.Manifest.permission.CAMERA},
                        REQUEST_CAMERA
                );
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Do something
                //Ex: display msg with product id get from view.getTag

                // selected item
                String selected =((TextView)view.findViewById(R.id.atomPay_name)).getText().toString();
                Toast toast=Toast.makeText(getApplicationContext(), selected, Toast.LENGTH_SHORT);
                toast.show();
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode==REQUEST_CAMERA){

            if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){

                Intent intent=new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,REQUEST_CAMERA);
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int i;
        if(resultCode==RESULT_OK){

                if (requestCode == REQUEST_CAMERA) {

                    Bundle bundle = data.getExtras();
                    final Bitmap bmp = (Bitmap) bundle.get("data");

                    final ImageView imageView = new ImageView(this);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(700,350);
                    params.setMargins(8, 8, 8, 8);
                    params.width=700;
                   // imageView.getLayoutParams().height = 20;
                    imageView.setLayoutParams(params);
                    imageView.setImageBitmap(bmp);
                    linearLayout.addView(imageView);
                    LinearLayout.LayoutParams params1=new LinearLayout.LayoutParams(60,60);
                    params1.setMargins(320,8,0,0);
                    final Button button=new Button(this);
                    button.setGravity(Gravity.CENTER);
                    button.setBackgroundResource(R.drawable.crosslist);
                    button.setLayoutParams(params1);
                    linearLayout.addView(button);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            linearLayout.removeView(imageView);
                            button.setVisibility(View.GONE);
                        }
                    });
                    //imageupload.setImageBitmap(bmp);

            }
        }
    }

    public void removeAtomPayOnClickHandler(View v) {
        AtomPayment itemToRemove = (AtomPayment)v.getTag();
        adapter.remove(itemToRemove);
    }

    private void setupListViewAdapter() {
        adapter = new AtomPayListAdapter(listItemAdd.this, R.layout.atom_pay_list_item, new ArrayList<AtomPayment>());
        ListView atomPaysListView = (ListView)findViewById(R.id.EnterPays_atomPaysList);

        atomPaysListView.setAdapter(adapter);
    }

    private void setupAddPaymentButton() {

        final EditText mainedittext= (EditText) findViewById(R.id.mainedittext);
        findViewById(R.id.EnterPays_addAtomPayment).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                linearLayout.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
                if(mainedittext.getText().toString().equals("")){

                    Toast.makeText(getApplicationContext(),"Plz Enter Product Name & Quantity",Toast.LENGTH_SHORT).show();

                }
                else {
                    adapter.insert(new AtomPayment(mainedittext.getText().toString(), 0), 0);
                    mainedittext.setText("");
                }

            }
        });
    }

    private String imageToString(Bitmap bitmap){

        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imageByte=byteArrayOutputStream.toByteArray();
        String encodeImage= Base64.encodeToString(imageByte,Base64.DEFAULT);
        return encodeImage;
    }

    public void showResult(View v) {
        mobonlist=mobilenumberonlisttext.getText().toString();
        areahome=areafromhome.getText().toString();
        String result = "Product are :";
        int totalAmount=0;
        for (AtomPayment p : adapter.getBox()) {
            if (p.box){
               result += "\n" + p.getName();
                /*result += "\n" + p.getValue();*/
               /* totalAmount+=p.getValue();*/
            }/*else {
                result += "\n" + p.getName();
                result += "\n" + p.getValue();
                totalAmount+=p.getValue();
            }*/
        }
        Toast.makeText(this, result+"\n", Toast.LENGTH_LONG).show();
       Intent intent=new Intent(getApplicationContext(),DeliveryDetails1.class);
        intent.putExtra("location", result);
        intent.putExtra("Number", mobonlist);
        intent.putExtra("Area", areahome);
        startActivity(intent);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(listItemAdd.this, PartnerStores.class));
        finish();

    }


}
