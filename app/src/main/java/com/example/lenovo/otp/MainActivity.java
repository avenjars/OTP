package com.example.lenovo.otp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    LoginDB db;
    EditText MobileNumber, OTPEditview, other;
    Button Submit, OTPButton, arrow, btnsubmit, firstBtn;
    TextView Textview, Otp, OTPEditviewstar, skipotp, area1;
    String[] list = {"Area", "Hadapsar", "Handewadi", "Fursungi", "Undri", "Other"};
    String result, areaCheck, mobnumb;
    private BroadcastReceiver receiver;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    boolean mVerificationInProgress = false;
    String mVerificationId;
    PhoneAuthProvider.ForceResendingToken mResendToken;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new LoginDB(getApplicationContext(), "login.db", null, 1);
        MobileNumber = (EditText) findViewById(R.id.mobileNumber);
        Submit = (Button) findViewById(R.id.submit);
        OTPEditview = (EditText) findViewById(R.id.ed_otp);
        OTPButton = (Button) findViewById(R.id.otp_button);
        Textview = (TextView) findViewById(R.id.textView);
        Otp = (TextView) findViewById(R.id.otp);
        arrow = findViewById(R.id.bt_arrow);
        OTPEditviewstar = findViewById(R.id.editotpstar);
        btnsubmit = findViewById(R.id.btn_submit);
        skipotp = findViewById(R.id.skipotp);
        firstBtn = findViewById(R.id.first_btn);
        other = findViewById(R.id.ev_other);
        area1 = findViewById(R.id.tv_area1);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, list);
        final Spinner spinner = findViewById(R.id.slist);
        spinner.setAdapter(arrayAdapter);
      /*  spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                result= (String) spinner.getSelectedItem();
                if(result.equalsIgnoreCase("Other"))
                {
                    other.setText(result);

                }



            }
        });*/
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                result = (String) spinner.getSelectedItem();
                if (result.equalsIgnoreCase("Other")) {
                    other.setVisibility(View.VISIBLE);
                 /* other.setText(result);*/

                } else {
                    other.setVisibility(View.GONE);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mAuth = FirebaseAuth.getInstance();


        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                areaCheck = other.getText().toString();
                mobnumb = MobileNumber.getText().toString();
                result = (String) spinner.getSelectedItem();
                if (result.equalsIgnoreCase("Area")) {
                    Toast.makeText(MainActivity.this, "Please Enter area first", Toast.LENGTH_LONG).show();

                } else if (areaCheck.equalsIgnoreCase("Undri") || areaCheck.equalsIgnoreCase("Hadapsar") || areaCheck.equalsIgnoreCase("Handevadi") || areaCheck.equalsIgnoreCase("Fursungi")) {

                    Intent intent = new Intent(getApplicationContext(), PartnerStores.class);
                    intent.putExtra("Number", mobnumb);
                    intent.putExtra("Area", areaCheck);


                    startActivity(intent);
                } else if (result.equalsIgnoreCase("Undri") || result.equalsIgnoreCase("Hadapsar") || result.equalsIgnoreCase("Handevadi") || result.equalsIgnoreCase("Fursungi")) {

                    Intent intent = new Intent(getApplicationContext(), PartnerStores.class);
                    intent.putExtra("Number", mobnumb);

                    intent.putExtra("Area", result);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getApplicationContext(), AreaNotfound.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Area not found", Toast.LENGTH_LONG).show();

                }

            }
        });

        OTPButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        skipotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PartnerStores.class);
                startActivity(intent);
            }
        });

        firstBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Enter OTP", Toast.LENGTH_LONG).show();

            }
        });


        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                db.doLogin(MobileNumber.getText().toString());
                Toast.makeText(MainActivity.this, "verification done", Toast.LENGTH_LONG).show();
                arrow.setVisibility(View.VISIBLE);
                OTPButton.setVisibility(View.GONE);
                btnsubmit.setVisibility(View.VISIBLE);
                area1.setVisibility(View.VISIBLE);
                OTPEditviewstar.setVisibility(View.VISIBLE);
                OTPEditview.setVisibility(View.GONE);
                spinner.setVisibility(View.VISIBLE);

                /*Intent i=new Intent(getApplicationContext(),AfterOTP.class);
                startActivity(i);*/
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(MainActivity.this, "verification fail", Toast.LENGTH_LONG).show();
                if (e instanceof FirebaseAuthInvalidCredentialsException) {

                    // Invalid request
                    // [START_EXCLUDE]
                    Toast.makeText(MainActivity.this, "invalid mob no", Toast.LENGTH_LONG).show();
                    // [END_EXCLUDE]
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    // [START_EXCLUDE]
                    Toast.makeText(MainActivity.this, "quta over", Toast.LENGTH_LONG).show();
                    // [END_EXCLUDE]
                }
            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                //Log.d(TAG, "onCodeSent:" + verificationId);
                Toast.makeText(MainActivity.this, "Verification code sent to mobile", Toast.LENGTH_LONG).show();
                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;
               /* MobileNumber.setVisibility(View.GONE);
                Submit.setVisibility(View.GONE);
                Textview.setVisibility(View.GONE);
                OTPButton.setVisibility(View.VISIBLE);
                OTPEditview.setVisibility(View.VISIBLE);
                Otp.setVisibility(View.VISIBLE);*/
                firstBtn.setVisibility(View.GONE);
                OTPButton.setVisibility(View.VISIBLE);
            }
        };


        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + MobileNumber.getText().toString(),        // Phone number to verify
                        60,                 // Timeout duration
                        TimeUnit.SECONDS,   // Unit of timeout
                        MainActivity.this,               // Activity (for callback binding)
                        mCallbacks);        // OnVerificationStateChangedCallbacks
            }
        });

        OTPButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, OTPEditview.getText().toString());
                signInWithPhoneAuthCredential(credential);
            }
        });

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equalsIgnoreCase("otp")) {
                    final String message = intent.getStringExtra("message");
                    //Do whatever you want with the code here

                    OTPEditview.setText(message);

                }

            }
        };


    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(receiver, new IntentFilter("OTP"));

        /*SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean previouslyStarted = prefs.getBoolean(getString(R.string.pref_previously_started), false);
        if(!previouslyStarted) {
            SharedPreferences.Editor edit = prefs.edit();
            edit.putBoolean(getString(R.string.pref_previously_started), Boolean.TRUE);
            edit.commit();
            showHelp();
        }*/


    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(receiver);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, list);
        final Spinner spinner = findViewById(R.id.slist);
        spinner.setAdapter(arrayAdapter);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "signInWithCredential:success");
                            db.doLogin(MobileNumber.getText().toString());
                            Toast.makeText(MainActivity.this, "Verification done", Toast.LENGTH_LONG).show();
                            FirebaseUser user = task.getResult().getUser();
                            arrow.setVisibility(View.VISIBLE);
                            OTPButton.setVisibility(View.GONE);
                            btnsubmit.setVisibility(View.VISIBLE);
                            area1.setVisibility(View.VISIBLE);
                            OTPEditviewstar.setVisibility(View.VISIBLE);
                            OTPEditview.setVisibility(View.GONE);
                            spinner.setVisibility(View.VISIBLE);
                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI
                            //Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(MainActivity.this, "Verification failed code invalid", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }

    /*public void submitbtn(View v){

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,list);
        final Spinner spinner=findViewById(R.id.slist);
        spinner.setAdapter(arrayAdapter);

        areaCheck=other.getText().toString();
        result= (String) spinner.getSelectedItem();
        if(areaCheck.equalsIgnoreCase("Undri")||areaCheck.equalsIgnoreCase("Hadapsar")||areaCheck.equalsIgnoreCase("Handevadi")||areaCheck.equalsIgnoreCase("Fursungi")){

            Intent intent=new Intent(getApplicationContext(),PartnerStores.class);
            startActivity(intent);
        }else if(result.equalsIgnoreCase("Undri")||result.equalsIgnoreCase("Hadapsar")||result.equalsIgnoreCase("Handevadi")||result.equalsIgnoreCase("Fursungi")){

            Intent intent=new Intent(getApplicationContext(),PartnerStores.class);
            startActivity(intent);
        }

        else {
            Intent intent=new Intent(getApplicationContext(),AreaNotfound.class);
            startActivity(intent);
            Toast.makeText(MainActivity.this,"Area not found",Toast.LENGTH_LONG).show();

        }



    }*/


}
