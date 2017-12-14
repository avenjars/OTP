package com.example.lenovo.otp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class TermsAndCondition extends AppCompatActivity {
    Button dotmenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_condition);

        dotmenu=findViewById(R.id.menu_dots2);

        dotmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(TermsAndCondition.this, dotmenu);
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
                                TermsAndCondition.this,
                                "You Clicked : " + item.getTitle(),
                                Toast.LENGTH_SHORT
                        ).show();
                        return true;
                    }
                });

                popup.show(); //showing popup menu
            }
        });
    }
}
