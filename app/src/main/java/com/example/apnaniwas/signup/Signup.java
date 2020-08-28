package com.example.apnaniwas.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apnaniwas.BottomNavigationBar;
import com.example.apnaniwas.apnaniwasDB.model.signupresponse.SignResponse;
import com.example.apnaniwas.util.SharedPreference;
import com.example.apnaniwas.apnaniwasDB.connection.APIService;
import com.example.apnaniwas.apnaniwasDB.connection.RestClient;
import com.example.apnaniwas.apnaniwasDB.model.signupresponse.MemberModel;
import com.example.apnaniwas.R;
import com.example.apnaniwas.tmp.TmpProfile;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class Signup extends AppCompatActivity {
    private static Signup instance;
    private EditText Fname,Lname,email, password,phoneno,confirmPswd;
    private Button btnRegister;
    SharedPreferences sharedPreferences;
    private String UsrFname,UsrLname ,Usremail,Usrpswd,UsrConfirmPswd,Usrmob;
    private static APIService apiService = RestClient.createService(APIService.class);
    private CompositeDisposable disposable = new CompositeDisposable();


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
                register(mVerifiedMob);
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


    private void register(String mVerifiedMob) {


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
                    , Usrpswd)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableObserver<SignResponse>() {

                        @Override
                        public void onNext(SignResponse signResponse) {

/*                            signResponse.getMemberId();
                            signResponse.getEmailId();
                            signResponse.getMemberPassword();
                            signResponse.getMemberName();
                            signResponse.getPhoneNo();*/


                            SharedPreference.getInstance(getApplicationContext()).userRegister(signResponse);
                            SharedPreference.getInstance(getApplicationContext()).setFirstTimeLaunch(true);
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
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

}
