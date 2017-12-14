package com.example.lenovo.otp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;

public class partenerStoreList extends AppCompatActivity {
    private ListView lvProduct;
    private ProductListAdapter adapter;
    private List<Product> mProductList;
    Button OrderNow,menubar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partener_store_list);

        lvProduct = (ListView)findViewById(R.id.listview_product);
        menubar=findViewById(R.id.menubar);

        OrderNow=findViewById(R.id.ordernow);
        OrderNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),listItemAdd.class);
                startActivity(intent);

            }


        });

        menubar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(partenerStoreList.this, menubar);
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
                                partenerStoreList.this,
                                "You Clicked : " + item.getTitle(),
                                Toast.LENGTH_SHORT
                        ).show();
                        return true;
                    }
                });

                popup.show(); //showing popup menu
            }
        }); //closing the setOnClickListener method

        mProductList = new ArrayList<>();
        //Add sample data for list
        //We can get data from DB, webservice here
        mProductList.add(new Product(1, "Mahalaxmi Super Market", "24 jun|9:00AM", "Delivery Person: Ramesh Mishra","+917030537378"));
        mProductList.add(new Product(3, "Om Super Market","24 jun|9:00AM", "Delivery Person: Ramesh Mishra","+917030537378"));
        mProductList.add(new Product(4, "Vishaka Super Market","24 jun|9:00AM", "Delivery Person: Ramesh Mishra","+917030537378"));
        mProductList.add(new Product(5, "Mahalaxmi Super Market", "24 jun|9:00AM", "Delivery Person: Ramesh Mishra","+917030537378"));
        mProductList.add(new Product(6, "Mahalaxmi Super Market", "24 jun|9:00AM", "Delivery Person: Ramesh Mishra","+917030537378"));
        mProductList.add(new Product(7, "Mahalaxmi Super Market", "24 jun|9:00AM", "Delivery Person: Ramesh Mishra","+917030537378"));
        mProductList.add(new Product(8, "Mahalaxmi Super Market", "24 jun|9:00AM", "Delivery Person: Ramesh Mishra","+917030537378"));
        mProductList.add(new Product(9, "Mahalaxmi Super Market", "24 jun|9:00AM", "Delivery Person: Ramesh Mishra","+917030537378"));
        mProductList.add(new Product(10, "Mahalaxmi Super Market", "24 jun|9:00AM", "Delivery Person: Ramesh Mishra","+917030537378"));
        mProductList.add(new Product(11, "Mahalaxmi Super Market", "24 jun|9:00AM", "Delivery Person: Ramesh Mishra","+917030537378"));

        //Init adapter
        adapter = new ProductListAdapter(getApplicationContext(), mProductList);
        lvProduct.setAdapter(adapter);

        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Do something
                //Ex: display msg with product id get from view.getTag
                Toast.makeText(getApplicationContext(), "Clicked product id =" + view.getTag(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
