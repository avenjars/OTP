package com.example.lenovo.otp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class PartnerStores extends AppCompatActivity {
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] XMEN= {R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four,R.drawable.five};
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();


    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;
    EditText addtojar;
    TextView seeall,partnerstores,contactnumber,areafrommain,area123;
    Button OrderNow,mjbtn,menudots,btnadd;
    DrawerLayout drawerLayout;
    RelativeLayout relativeLayout;
    String contactnum,areamain; //mobile number from main activity




    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_stores);
        contactnumber=findViewById(R.id.tv_contactnumber);
        drawerLayout=findViewById(R.id.drawerLayout);
        seeall= (TextView) findViewById(R.id.seeall);
        addtojar=findViewById(R.id.ev_addtojar);
        mjbtn=findViewById(R.id.btnmj);
        partnerstores=findViewById(R.id.partenerstores);
        menudots=findViewById(R.id.menu_dots);
        relativeLayout=findViewById(R.id.relativeLayout);
        expListView=findViewById(R.id.lvExp);
        btnadd=findViewById(R.id.btnadd);
        area123=findViewById(R.id.area123);
        Intent intent = getIntent();
        String str = intent.getStringExtra("Number");
        contactnumber.setText(str);
        contactnum=contactnumber.getText().toString();

        areafrommain=findViewById(R.id.tv_areafrommain);

        Intent i = getIntent();
        String area = i.getStringExtra("Area");
        areafrommain.setText(area);
       /* area123.setText(area);*/
        areamain=areafrommain.getText().toString();

        addtojar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(), listItemAdd.class);
                intent.putExtra("Number", contactnum);
                intent.putExtra("Area", areamain);
                startActivity(intent);

            }
        });

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(), listItemAdd.class);
                intent.putExtra("Number", contactnum);
                intent.putExtra("Area", areamain);
                startActivity(intent);

            }
        });

        mjbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(drawerLayout.isDrawerOpen(Gravity.LEFT)){

                    drawerLayout.closeDrawer(Gravity.LEFT);
                }
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        OrderNow=findViewById(R.id.ordernow);
        OrderNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(), listItemAdd.class);

                intent.putExtra("Number", contactnum);
                intent.putExtra("Area", areamain);
                startActivity(intent);

            }
        });


        seeall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),expandedlistview.class);

                intent.putExtra("Number", contactnum);
                intent.putExtra("Area", areamain);
                startActivity(intent);


            }
        });

        partnerstores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),expandedlistview.class);
                startActivity(intent);


            }
        });

        menudots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(PartnerStores.this, menudots);
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
                                PartnerStores.this,
                                "You Clicked : " + item.getTitle(),
                                Toast.LENGTH_SHORT
                        ).show();
                        return true;
                    }
                });

                popup.show(); //showing popup menu
            }
        }); //closing the setOnClickListener method


        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);



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

        init();



    }

    private void init() {
        for(int i=0;i<XMEN.length;i++)
            XMENArray.add(XMEN[i]);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new MyAdapter(PartnerStores.this,XMENArray));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == XMEN.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2500, 2500);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Are you sure?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                }).setNegativeButton("no", null).show();
    }
}
