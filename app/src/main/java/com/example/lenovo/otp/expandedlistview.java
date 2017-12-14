package com.example.lenovo.otp;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class expandedlistview extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;
    EditText addtojar;
    TextView seeall,contactnumberfromhome,areafromhome;
    Button OrderNow,mjbtn,menudots,btnadd;
    DrawerLayout drawerLayout;
    RelativeLayout relativeLayout;
    String contactnum1,areamain1;




    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandedlistview);

        expListView=findViewById(R.id.lvExp);
        menudots=findViewById(R.id.menu_dots);
        btnadd=findViewById(R.id.btnadd1);
        addtojar=findViewById(R.id.ev_addtojar1);

        contactnumberfromhome=findViewById(R.id.tv_contactnumberfromhome1);
        Intent intent = getIntent();
        String str = intent.getStringExtra("Number");
        contactnumberfromhome.setText(str);
        contactnum1=contactnumberfromhome.getText().toString();


        areafromhome=findViewById(R.id.tv_areafrommainfromhome);
        Intent i = getIntent();
        String area = i.getStringExtra("Area");
        areafromhome.setText(area);
        areamain1=areafromhome.getText().toString();

        addtojar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(), listItemAdd.class);
                intent.putExtra("Number", contactnum1);
                intent.putExtra("Area", areamain1);
                startActivity(intent);

            }
        });

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(), listItemAdd.class);
                intent.putExtra("Number", contactnum1);
                intent.putExtra("Area", areamain1);
                startActivity(intent);

            }
        });

        OrderNow=findViewById(R.id.ordernow1);
        OrderNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(), listItemAdd.class);

                intent.putExtra("Number", contactnum1);
                intent.putExtra("Area", areamain1);
                startActivity(intent);

            }
        });




        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
        expListView.expandGroup(0);
        expListView.expandGroup(1);
        expListView.expandGroup(2);
        expListView.expandGroup(3);


    }

    public int GetPixelFromDips(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {

            expListView.setIndicatorBounds(width-GetPixelFromDips(75), width-GetPixelFromDips(5));

        } else {
            expListView.setIndicatorBoundsRelative(width-GetPixelFromDips(75), width-GetPixelFromDips(5));
        }
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Groceries & consumables");
        listDataHeader.add("Home & Personal Care");
        listDataHeader.add("Vegetable & fruits");
        listDataHeader.add("Speciality Stores");

        // Adding child data
        List<String> first = new ArrayList<String>();
        first.add("one");
        first.add("two");
        first.add("three");


        List<String> second = new ArrayList<String>();
        second.add("1");
        second.add("2");
        second.add("3");

        List<String> third = new ArrayList<String>();
        third.add("A");
        third.add("B");
        third.add("C");
        List<String> forth = new ArrayList<String>();
        forth.add("AA");
        forth.add("BB");
        forth.add("CC");


        listDataChild.put(listDataHeader.get(0), first); // Header, Child data
        listDataChild.put(listDataHeader.get(1), second);
        listDataChild.put(listDataHeader.get(2), third);
        listDataChild.put(listDataHeader.get(3), forth);





    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(expandedlistview.this, PartnerStores.class));
        finish();

    }
}
