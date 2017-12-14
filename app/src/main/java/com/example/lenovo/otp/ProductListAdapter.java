package com.example.lenovo.otp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by NgocTri on 11/15/2015.
 */
public class ProductListAdapter extends BaseAdapter {


    private Context mContext;
    private List<Product> mProductList;

    //Constructor

    public ProductListAdapter(Context mContext, List<Product> mProductList) {

        this.mContext = mContext;
        this.mProductList = mProductList;
    }

    @Override
    public int getCount() {
        return mProductList.size();
    }

    @Override
    public Object getItem(int position) {
        return mProductList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       /* ViewHolder mainViewholder = null;
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(layout, parent, false);
            ViewHolder viewHolder = new ViewHolder();

            *//*viewHolder.button = (Button) convertView.findViewById(R.id.list_item_btn);*//*
        }*/

        View v = View.inflate(mContext, R.layout.item_product_list, null);
        TextView tvName = (TextView)v.findViewById(R.id.tv_name);
        TextView tvPrice = (TextView)v.findViewById(R.id.tv_price);
        TextView tvDescription = (TextView)v.findViewById(R.id.tv_description);
        TextView tvPhone=(TextView)v.findViewById(R.id.tv_phone);
        TextView tvId=(TextView)v.findViewById(R.id.tv_orderId);
        final ConstraintLayout layout1=v.findViewById(R.id.layoutconstran);
        //Set text for TextView
        tvName.setText(mProductList.get(position).getName());
        tvPrice.setText(String.valueOf(mProductList.get(position).getPrice()) + " ");
        tvDescription.setText(mProductList.get(position).getDescription());
        tvPhone.setText(mProductList.get(position).getPhone());
        tvId.setText("Order ID:"+String.valueOf(mProductList.get(position).getId()) + " ");
        Button btn1=(Button)v.findViewById(R.id.tv_btn1);
        Button btn2=(Button)v.findViewById(R.id.tv_btn2);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0123456789"));
                mContext.startActivity(intent);

                Toast.makeText(mContext, "Calling plese wait... ", Toast.LENGTH_SHORT).show();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "order canceled successfully ", Toast.LENGTH_SHORT).show();

            }
        });
        tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             layout1.setVisibility(View.VISIBLE);

            }
        });


        //Save product id to tag
        v.setTag(mProductList.get(position).getId());


        return v;
    }
}


