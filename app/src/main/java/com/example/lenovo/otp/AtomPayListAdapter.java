package com.example.lenovo.otp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class AtomPayListAdapter extends ArrayAdapter<AtomPayment> {

	protected static final String LOG_TAG = AtomPayListAdapter.class.getSimpleName();

	private List<AtomPayment> items;
	private int layoutResourceId;
	private Context context;
	String res;
	String gm,kg,lit;

	public AtomPayListAdapter(Context context, int layoutResourceId, List<AtomPayment> items) {
		super(context, layoutResourceId, items);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.items = items;
	}



	@Override
	public View getView(int position, final View convertView, ViewGroup parent) {
		View row = convertView;
		AtomPaymentHolder holder = null;






		final LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		row = inflater.inflate(layoutResourceId, parent, false);

		holder = new AtomPaymentHolder();
		holder.atomPayment = items.get(position);
		holder.removePaymentButton = (ImageButton)row.findViewById(R.id.atomPay_removePay);
		holder.removePaymentButton.setTag(holder.atomPayment);

		holder.name = (TextView)row.findViewById(R.id.atomPay_name);
		setNameTextChangeListener(holder);
		holder.value = (TextView)row.findViewById(R.id.atomPay_value);
		setValueTextListeners(holder);
		final Button add=row.findViewById(R.id.add);
	//	final EditText editvalue=row.findViewById(R.id.atomPay_value);

		AtomPayment p = getProduct(position);



		final CheckBox cbBuy = (CheckBox) row.findViewById(R.id.cbBox);
		cbBuy.setChecked(true);


		cbBuy.setOnCheckedChangeListener(myCheckChangList);
		cbBuy.setTag(position);
		cbBuy.setChecked(p.box);

        ConstraintLayout layout1=row.findViewById(R.id.layoyt2);

        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*cbBuy.setChecked(true);*/

            }
        });











		final EditText countervalue=row.findViewById(R.id.atomPay_value);
		TextView name =row.findViewById(R.id.atomPay_name);

		String i=name.getText().toString();

		if (i.equalsIgnoreCase(" ")) {
			cbBuy.setChecked(false);
		} else if(i.equalsIgnoreCase("0")){
			cbBuy.setChecked(false);
		}else {
			cbBuy.setChecked(true);

		}
/*
		countervalue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                *//*cbBuy.setChecked(true);*//*


            }
        });*/

			/*countervalue.addTextChangedListener(new TextWatcher() {
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count, int after) {

				}

				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {



				}

				@Override
				public void afterTextChanged(Editable s) {

					/*//*int i= Integer.parseInt(countervalue.getText().toString());*//**//*
					String i=countervalue.getText().toString();

					if (i.equalsIgnoreCase(" ")) {
						cbBuy.setChecked(false);
					} else if(i.equalsIgnoreCase("0")){
						cbBuy.setChecked(false);
					}else {
						cbBuy.setChecked(true);

					}

				}
			});*/


        cbBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	countervalue.setText(" ");

            }
        });


        /*nccriment and dicriment buttoms */
		/*final Button button1=row.findViewById(R.id.decrease);
        final Button button2=row.findViewById(R.id.increase);
		button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                *//*cbBuy.setChecked(true);*//*
               String s1= countervalue.getText().toString();
                Integer ss1=Integer.parseInt(s1);
                ss1--;
                s1=ss1.toString();
                countervalue.setText(s1);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                *//*cbBuy.setChecked(true);*//*
                String s1= countervalue.getText().toString();
                Integer ss1=Integer.parseInt(s1);
                ss1++;
                s1=ss1.toString();
                countervalue.setText(s1);




            }
        });*/





		/*AtomPayment p=items.get(position);
		holder.name.setText(p.getName());
		holder.value.setText(p.getValue());*/




		/*add.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				*//*editvalue.setVisibility(View.VISIBLE);*//*
				add.setVisibility(View.GONE);
                countervalue.setVisibility(View.VISIBLE);
                button1.setVisibility(View.VISIBLE);
                button2.setVisibility(View.VISIBLE);

			}
		});*/









		row.setTag(holder);

		setupItem(holder);
		return row;
	}

	AtomPayment getProduct(int position) {
		return ((AtomPayment) getItem(position));
	}

	ArrayList<AtomPayment> getBox() {
		ArrayList<AtomPayment> box = new ArrayList<AtomPayment>();
		for (AtomPayment p : items) {
			if (p.box)
				box.add(p);
		}
		return box;
	}





	CompoundButton.OnCheckedChangeListener myCheckChangList = new CompoundButton.OnCheckedChangeListener() {
		public void onCheckedChanged(CompoundButton buttonView,
									 boolean isChecked) {
			getProduct((Integer) buttonView.getTag()).box = isChecked;
		}
	};







	private void setupItem(AtomPaymentHolder holder) {
		holder.name.setText(holder.atomPayment.getName());
		holder.value.setText(String.valueOf(holder.atomPayment.getValue()));
	}

	public static class AtomPaymentHolder {
		AtomPayment atomPayment;
		TextView name;
		TextView value;
		ImageButton removePaymentButton;


	}

	private void setNameTextChangeListener(final AtomPaymentHolder holder) {
		holder.name.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				holder.atomPayment.setName(s.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

			@Override
			public void afterTextChanged(Editable s) { }


		});



	}

	private void setValueTextListeners(final AtomPaymentHolder holder) {
		holder.value.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				try{
					holder.atomPayment.setValue(s.toString());
				}catch (NumberFormatException e) {
					Log.e(LOG_TAG, "error reading double value: " + s.toString());
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

			@Override
			public void afterTextChanged(Editable s) { }
		});
	}




}
