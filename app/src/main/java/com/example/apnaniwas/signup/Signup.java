package com.example.apnaniwas.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apnaniwas.AddDetailsActivity;
import com.example.apnaniwas.BottomNavigationBar;
import com.example.apnaniwas.apnaniwasDB.model.signupresponse.SignResponse;
import com.example.apnaniwas.login.Login;
import com.example.apnaniwas.util.FirebaseTokenPref;
import com.example.apnaniwas.util.SharedPreference;
import com.example.apnaniwas.apnaniwasDB.connection.APIService;
import com.example.apnaniwas.apnaniwasDB.connection.RestClient;
import com.example.apnaniwas.R;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class Signup extends AppCompatActivity {
    private static Signup instance;
    private EditText Fname,Lname,email, password,phoneno,confirmPswd;
    private Button btnRegister;
    SharedPreferences sharedPreferences;
    private String UsrFname,UsrLname ,Usremail,Usrpswd,UsrConfirmPswd,Usrmob;
    private static APIService apiService = RestClient.createService(APIService.class);
    private CompositeDisposable disposable = new CompositeDisposable();
    private ProgressDialog progressDialog;
    private  String deviceToken="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Fname = findViewById(R.id.et_userFname);
        Lname = findViewById(R.id.et_userLname);
        email = findViewById(R.id.et_useremail );
        password = findViewById(R.id.et_RegPassword );
        confirmPswd = findViewById(R.id.et_ConfmPassword);
        btnRegister = findViewById(R.id.btnRegister);
        //Fname.addTextChangedListener(RegisterTextWatch);


        Intent intent = getIntent();
        String mVerifiedMob = intent.getStringExtra("VERIFIED MOBILENO");

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sentTokenToServer()){
                    register(mVerifiedMob,deviceToken);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Failed to register device.", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), Login.class));
                    finish();
                }
            }
        });

    }

/*
    private TextWatcher RegisterTextWatch = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            UsrFname = Fname.getText().toString().trim();
            UsrLname = Lname.getText().toString().trim();
            Usremail = email.getText().toString().trim();
            Usrpswd = mPassword.getText().toString().trim();
            UsrConfirmPswd = confirmPswd.getText().toString().trim();
            Usrmob = phoneno.getText().toString().trim();

            Boolean mFieldEnable = UsrFname.isEmpty() && UsrLname.isEmpty() && Usremail.isEmpty() && Usrpswd.isEmpty()  && Usrmob.isEmpty() && UsrConfirmPswd.isEmpty();
           // Log.w("RESULT :: >> ",mFieldEnable.toString());
            btnRegister.setEnabled(!mFieldEnable);
        }

        @Override
        public void afterTextChanged(Editable s) {a
        }
    };
*/
    // For Sending Activity Context public static Signup getContext(){ return instance; }


    private void register(String mVerifiedMob, String deviceToken) {


        UsrFname = Fname.getText().toString().trim();
        UsrLname = Lname.getText().toString().trim();
        Usremail = email.getText().toString().trim();
        Usrpswd = password.getText().toString().trim();
        UsrConfirmPswd = confirmPswd.getText().toString().trim();


/*      boolean isUsrFNameValid = validateUsername(UsrFname);
        boolean isUsrLNameValid = validateUsername(UsrLname);
        boolean isUsrEmailValid = validateEmail(Usremail);
        boolean isUsrPswdValid = validatePassword(Usrpswd);
        boolean isUsrMobValid = validateMobile(Usrmob);

        if (UsrFname.isEmpty()) {Fname.setError("Field can't be empty");}
        else if(!isUsrFNameValid) {Fname.setError("Name is too long");}

        if (UsrLname.isEmpty()) {Lname.setError("Field can't be empty");}
        else if(!isUsrLNameValid) {Lname.setError("Name is too long");}

        if (Usremail.isEmpty()) {email.setError("Field can't be empty");}
        else if(!isUsrEmailValid) {email.setError("Please enter a valid email address");}

        if (Usrpswd.isEmpty()) {mPassword.setError("Field can't be empty");}
        else if(!isUsrPswdValid) {mPassword.setError("Password is too weak");}

        if (Usrmob.isEmpty()) {phoneno.setError("Field can't be empty");}
        else if(!isUsrMobValid) {phoneno.setError("Please enter a valid mobile number");}

        if (UsrConfirmPswd.isEmpty()) { confirmPswd.setError("Field can't be empty"); }
        else if (!(UsrConfirmPswd.equals(Usrpswd))){ confirmPswd.setError("Field does not match with mPassword"); }*/

       // else {
            String Usrname = UsrFname + " "+ UsrLname;
        disposable.add( apiService.addMember("add_member", Usrname, mVerifiedMob, Usremail
                    , Usrpswd,deviceToken)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleSuccess, this::handleError)); }

    private void handleSuccess(SignResponse signResponse) {

        final int  STATUS_CODE = signResponse.getStatus();
        if(STATUS_CODE == 200)
        {
            SharedPreference.getInstance(getApplicationContext()).userRegister(signResponse);
            SharedPreference.getInstance(getApplicationContext()).setFirstTimeLaunch(true);
            startActivity(new Intent(getApplicationContext(), AddDetailsActivity.class));
            finish();
        }
        else
        {
            Toast.makeText(this,signResponse.getMesssage(),Toast.LENGTH_SHORT).show();
        }
    }
    private void handleError(Throwable e) {
        Log.e("FETCH ERROR  ", "onError: " + e.getMessage());
    }


    private boolean sentTokenToServer() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registering Device...");
        progressDialog.setCancelable(true);
        progressDialog.show();

       deviceToken = FirebaseTokenPref.getInstance(getApplicationContext()).getDeviceToken();

        if (deviceToken == null) {
            progressDialog.dismiss();
       //     Toast.makeText(this, "Token not generated.", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }

}

//Just old code if above code works fine then delete below one .
/*    @Override
                        public void onNext(SignResponse signResponse) {

*//*                           signResponse.getMemberId();
                            signResponse.getEmailId();
                            signResponse.getMemberPassword();
                            signResponse.getMemberName();
                            signResponse.getPhoneNo();*//*


                            SharedPreference.getInstance(getApplicationContext()).userRegister(signResponse);
                            SharedPreference.getInstance(getApplicationContext()).setFirstTimeLaunch(true);

                            sentTokenToServer();

                            startActivity(new Intent(getApplicationContext(), BottomNavigationBar.class));
                            finish();

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("ERROR", "onError: " + e.getMessage());
                        }

                        @Override
                        public void onComplete() {
                        }
                    }));
    }*/
